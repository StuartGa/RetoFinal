package com.example.capstone_project.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_project.data.entities.model.Bid
import com.example.capstone_project.databinding.CriptoBidsItemBinding

class BidsAdapter() : ListAdapter<Bid, BidsAdapter.ViewHolder>(difCallback) {
    companion object {
        var difCallback = object : DiffUtil.ItemCallback<Bid>() {
            override fun areItemsTheSame(oldItem: Bid, newItem: Bid): Boolean =
                oldItem.book == newItem.book
            override fun areContentsTheSame(oldItem: Bid, newItem: Bid): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val bookBinding = CriptoBidsItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(bookBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.enlazarItem(getItem(position))
    }

    inner class ViewHolder(val binding: CriptoBidsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun enlazarItem(bidsModel: Bid) {
            binding.txtBookBids.text = bidsModel.book
            binding.txtPriceBids.text = bidsModel.price
            binding.txtAmountBids.text = bidsModel.amount
        }
    }
}
