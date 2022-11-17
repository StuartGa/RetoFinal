package com.example.capstoneproject.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.databinding.CriptoBidsItemBinding
import com.example.capstoneproject.domain.model.BidDomain

class BidsAdapter() : ListAdapter<BidDomain, BidsAdapter.ViewHolder>(difCallback) {
    companion object {
        var difCallback = object : DiffUtil.ItemCallback<BidDomain>() {
            override fun areItemsTheSame(oldItem: BidDomain, newItem: BidDomain): Boolean =
                oldItem.book == newItem.book
            override fun areContentsTheSame(oldItem: BidDomain, newItem: BidDomain): Boolean =
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
        fun enlazarItem(bidsModel: BidDomain) {
            binding.txtBookBids.text = bidsModel.book
            binding.txtPriceBids.text = bidsModel.price
            binding.txtAmountBids.text = bidsModel.amount
        }
    }
}
