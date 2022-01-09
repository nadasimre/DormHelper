package com.example.dormhelper.screens.network.dormsfromnetwork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dormhelper.databinding.NetworkItemBinding
import com.example.dormhelper.model.NetDorm

class DormAdapter(private val clickListener: DormListener) : ListAdapter<NetDorm, DormAdapter.ViewHolder>(DormDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NetworkItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DormAdapter.ViewHolder, position: Int) {
        val dorm = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(dorm)
        }
        holder.bind(dorm)
    }

    class ViewHolder(private var binding:
                                 NetworkItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dorm: NetDorm) {
            binding.dorm = dorm
            binding.executePendingBindings()
        }

    }

    class DormListener(val clickListener: (dorm: NetDorm) -> Unit) {
        fun onClick(dorm: NetDorm) = clickListener(dorm)
    }

    class DormDiffCallback : DiffUtil.ItemCallback<NetDorm>() {

        override fun areItemsTheSame(oldItem: NetDorm, newItem: NetDorm): Boolean {
            return oldItem.houseNumber == newItem.houseNumber
        }

        override fun areContentsTheSame(oldItem: NetDorm, newItem: NetDorm): Boolean {
            return oldItem == newItem
        }
    }
}