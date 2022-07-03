package com.example.atginterntask1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.atginterntask1.databinding.RecyclerViewBinding
import com.example.atginterntask1.pojo.Photo

class SearchAdapter (private val items : List<Photo>) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Photo){
            Glide.with(binding.imageViewRecyclerView)
                .load(data.url_s)
                .into(binding.imageViewRecyclerView)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}