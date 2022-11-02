package com.example.capstone_project.data.network.entities.response

import com.example.capstone_project.data.network.entities.model.Book
import com.google.gson.annotations.SerializedName

data class AvaibleBookResponse(
    @SerializedName("success") val isSucces: Boolean,
    @SerializedName("payload") val books: List<Book>
)
