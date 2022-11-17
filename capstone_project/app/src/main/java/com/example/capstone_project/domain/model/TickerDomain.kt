package com.example.capstoneproject.domain.model

import com.example.capstoneproject.data.local.entities.TickerEntity
import com.example.capstoneproject.data.network.entities.model.Ticker

data class TickerDomain(
    val book: String,
    val last: String,
    val high: String,
    val low: String
)

fun Ticker.toDomain() = TickerDomain(
    book = book,
    last = last,
    high = high,
    low = low
)
fun TickerEntity.toDomain() = TickerDomain(
    book = book,
    last = last,
    high = high,
    low = low
)
