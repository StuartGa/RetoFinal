package com.example.capstoneproject.domain.model

import com.example.capstoneproject.data.local.entities.BidsEntity
import com.example.capstoneproject.data.network.entities.model.Bid

data class BidDomain(
    val book: String,
    val price: String,
    val amount: String
)
fun Bid.toDomain() =
    BidDomain(
        book = book,
        price = price,
        amount = amount
    )
fun BidsEntity.toDomain() =
    BidDomain(
        book = book,
        price = price,
        amount = amount
    )
