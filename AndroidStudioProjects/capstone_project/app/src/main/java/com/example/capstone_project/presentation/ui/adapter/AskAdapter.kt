package com.example.capstone_project.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_project.data.entities.model.Ask
import com.example.capstone_project.databinding.CriptoBidsItemBinding

class AskAdapter() : ListAdapter<Ask, AskAdapter.ViewHolder>(difCallback) {

    companion object {
        var difCallback = object : DiffUtil.ItemCallback<Ask>() {
            override fun areItemsTheSame(oldItem: Ask, newItem: Ask): Boolean =
                oldItem.book == newItem.book
            override fun areContentsTheSame(oldItem: Ask, newItem: Ask): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = CriptoBidsItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.enlazarItem(getItem(position))
    }

    class ViewHolder(val binding: CriptoBidsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun enlazarItem(asksModel: Ask) {
            binding.txtBookBids.text = asksModel.book
            binding.txtPriceBids.text = asksModel.price
            binding.txtAmountBids.text = asksModel.amount
        }
    }
}
