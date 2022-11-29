package com.example.capstone_project.domain.model

import com.example.capstone_project.data.network.entities.model.Bid
import com.example.capstone_project.data.local.entities.AskEntity
import com.example.capstone_project.data.local.entities.BidsEntity

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
