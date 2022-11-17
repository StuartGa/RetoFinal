package com.example.capstoneproject.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.capstoneproject.domain.model.TickerDomain

@Entity(tableName = "ticker")
data class TickerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTicker")
    val idTicker: Int = 0,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "last") val last: String,
    @ColumnInfo(name = "high") val high: String,
    @ColumnInfo(name = "low") val low: String
)
fun TickerDomain.toDatabase() = TickerEntity(
    book = book,
    last = last,
    high = high,
    low = low
)
