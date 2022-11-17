package com.example.capstoneproject.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.databinding.CriptoBidsItemBinding
import com.example.capstoneproject.domain.model.AskDomain

class AskAdapter() : ListAdapter<AskDomain, AskAdapter.ViewHolder>(difCallback) {

    companion object {
        var difCallback = object : DiffUtil.ItemCallback<AskDomain>() {
            override fun areItemsTheSame(oldItem: AskDomain, newItem: AskDomain): Boolean =
                oldItem.book == newItem.book
            override fun areContentsTheSame(oldItem: AskDomain, newItem: AskDomain): Boolean =
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
        fun enlazarItem(asksModel: AskDomain) {
            binding.txtBookBids.text = asksModel.book
            binding.txtPriceBids.text = asksModel.price
            binding.txtAmountBids.text = asksModel.amount
        }
    }
}
