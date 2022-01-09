package com.example.dormhelper.screens.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dormhelper.databinding.ListItemPresetBinding
import com.example.dormhelper.model.DormPreset

class PresetAdapter(private val clickListener: PresetListener) : ListAdapter<DormPreset, PresetAdapter.ViewHolder>(PresetDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ViewHolder{
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item!!, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemPresetBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DormPreset, clickListener: PresetListener) {
            binding.preset = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemPresetBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class PresetListener(val clickListener: (DormId: Int) -> Unit) {
        fun onClick(preset: DormPreset) = clickListener(preset.id)
    }

    class PresetDiffCallback : DiffUtil.ItemCallback<DormPreset>() {

        override fun areItemsTheSame(oldItem: DormPreset, newItem: DormPreset): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DormPreset, newItem: DormPreset): Boolean {
            return oldItem == newItem
        }
    }
}