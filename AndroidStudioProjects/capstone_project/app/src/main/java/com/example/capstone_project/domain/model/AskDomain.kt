package com.example.capstone_project.domain.model

import com.example.capstone_project.data.network.entities.model.Ask
import com.example.capstone_project.data.local.entities.AskEntity

data class AskDomain(
    val book: String,
    val price: String,
    val amount: String
)
fun Ask.toDomain() =
    AskDomain(
        book = book,
        price = price,
        amount = amount
    )
fun AskEntity.toDomain() =
    AskDomain(
        book = book,
        price = price,
        amount = amount
    )
