package com.example.capstone_project.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.capstone_project.data.network.entities.model.Ask
import com.example.capstone_project.domain.model.AskDomain

@Entity(tableName = "ask")
data class AskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idAsk") val idAsk: Int = 0,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "amount") val amount: String
)

fun AskDomain.toDataBase() =
    AskEntity(
        book = book,
        price = price,
        amount = amount
    )