package com.example.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemKitsuBinding
import com.example.presentation.models.DataItemUI

class MangaAdapter: ListAdapter<DataItemUI, MangaAdapter.MangaViewHolder>(
    diffUtil
) {

    inner class MangaViewHolder(private val binding: ItemKitsuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(dataItem: DataItemUI) {
            binding.name.text = dataItem.attributes.titles.enJp
            Glide.with(binding.img).load(dataItem.attributes.posterImage.original)
                .into(binding.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        return MangaViewHolder(
            ItemKitsuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DataItemUI>() {
            override fun areItemsTheSame(oldItem: DataItemUI, newItem: DataItemUI): Boolean {
                return oldItem.attributes.titles == newItem.attributes.titles
            }

            override fun areContentsTheSame(oldItem: DataItemUI, newItem: DataItemUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}