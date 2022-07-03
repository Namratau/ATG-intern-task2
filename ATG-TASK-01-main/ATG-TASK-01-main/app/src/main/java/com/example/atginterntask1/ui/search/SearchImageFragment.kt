package com.example.atginterntask1.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.atginterntask1.R
import com.example.atginterntask1.adapters.RecyclerViewAdapter
import com.example.atginterntask1.adapters.SearchAdapter
import com.example.atginterntask1.databinding.FragmentHomeBinding
import com.example.atginterntask1.databinding.SearchImageFragmentBinding
import com.example.atginterntask1.ui.home.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive

@AndroidEntryPoint
class SearchImageFragment : Fragment() {

    private var _binding: SearchImageFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchImageViewModel by viewModels()

    private lateinit var recyclerViewAdapter : SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchImageFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.apply {
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            staggeredGridLayoutManager.gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            layoutManager = staggeredGridLayoutManager
            addItemDecoration(DividerItemDecoration(this.context.applicationContext, DividerItemDecoration.VERTICAL))
        }

        viewModel.recyclerListDataObserver().observe(viewLifecycleOwner, {
            if(!it.isNullOrEmpty()){
                Log.d("TAG", "onCreateView: ${it.size}")
                recyclerViewAdapter = SearchAdapter(it)
                binding.recyclerView.adapter = recyclerViewAdapter
            }else {
                Log.d("TAG", "onCreate: it is null or empty")
            }
        })

        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editQuery.doOnTextChanged { text, start, before, count ->
            if (isOnline(this.requireContext())) {
                Log.d("TAG", "onCreateView: ${text}")
                viewModel.onTextChanged(text.toString())
            } else {
                view.snack("Network Failed")
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }

}