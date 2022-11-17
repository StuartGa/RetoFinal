package com.example.capstoneproject.domain.model

import com.example.capstoneproject.data.local.entities.BookEntity
import com.example.capstoneproject.data.network.entities.model.Book

data class BookDomain(
    val book: String,
    val minium_amount: Double,
    val minium_price: Double,
    val minium_value: Double
)

fun Book.toDomain() =
    BookDomain(
        book = book,
        minium_amount = minium_amount,
        minium_price = minium_price,
        minium_value = minium_value
    )

fun BookEntity.toDomain() =
    BookDomain(
        book = book,
        minium_amount = minium_amount,
        minium_price = minium_price,
        minium_value = minium_value
    )
