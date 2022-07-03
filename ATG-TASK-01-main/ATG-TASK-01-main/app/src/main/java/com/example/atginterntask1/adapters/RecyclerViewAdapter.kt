package com.example.atginterntask1.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.atginterntask1.R
import com.example.atginterntask1.adapters.RecyclerViewAdapter.MyViewHolder
import com.example.atginterntask1.databinding.RecyclerViewBinding
import com.example.atginterntask1.pojo.Photo

class RecyclerViewAdapter : PagingDataAdapter<Photo, MyViewHolder>(DiffCallback()) {

    class MyViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Photo){
            Log.d("TAG", "bind: data.url : ${data.url_s}")
            Glide.with(binding.imageViewRecyclerView)
                .load(data.url_s)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageViewRecyclerView)
        }
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }
    class DiffCallback : DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.url_s == newItem.url_s
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }

}
