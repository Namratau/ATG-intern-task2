package com.example.atginterntask1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.atginterntask1.adapters.RecyclerViewAdapter
import com.example.atginterntask1.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    private val TAG = "TAG"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.apply {
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, VERTICAL)
            staggeredGridLayoutManager.gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            layoutManager = staggeredGridLayoutManager
            addItemDecoration(DividerItemDecoration(this.context.applicationContext, DividerItemDecoration.VERTICAL))
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.characters.collectLatest {
                Log.d(TAG, "onCreateView: it has changed. ")
                recyclerViewAdapter.submitData(it)
            }
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}