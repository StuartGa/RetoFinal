package com.example.capstoneproject.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.capstoneproject.domain.model.BookDomain

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idBook")
    val idBook: Int = 0,
    @ColumnInfo(name = "book")
    val book: String,
    @ColumnInfo(name = "minium_amount") val minium_amount: Double,
    @ColumnInfo(name = "minium_price") val minium_price: Double,
    @ColumnInfo(name = "minium_value") val minium_value: Double
)

fun BookDomain.toDatabase() =
    BookEntity(
        book = book,
        minium_amount = minium_amount,
        minium_price = minium_price,
        minium_value = minium_value
    )
