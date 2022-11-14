package com.example.capstone_project.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_project.data.network.entities.model.Book
import com.example.capstone_project.databinding.CriptoCardBinding
import com.example.capstone_project.domain.model.BookDomain
import com.example.capstone_project.presentation.util.Util

class AvailableBookAdapter(
    private val listener: (BookDomain) -> Unit
) : ListAdapter<BookDomain, AvailableBookAdapter.ViewHolder>(difCallback) {

    companion object {
        var difCallback = object : DiffUtil.ItemCallback<BookDomain>() {
            override fun areItemsTheSame(oldItem: BookDomain, newItem: BookDomain): Boolean =
                oldItem.book == newItem.book
            override fun areContentsTheSame(oldItem: BookDomain, newItem: BookDomain): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val bookBinding = CriptoCardBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(bookBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.enlazarItem(getItem(position))
    }

    inner class ViewHolder(val binding: CriptoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun enlazarItem(bookModel: BookDomain) {
            binding.txtBookName.text = bookModel.book
            binding.txtMaximumPriceValue.text = bookModel.minium_value.toString()
            binding.txtMinimumPriceValue.text = bookModel.minium_price.toString()
            binding.imageBitcoin.setImageResource(Util.getResources(bookModel.book))

            binding.cardBook.setOnClickListener {
                listener(bookModel)
            }
        }
    }
}
