package com.example.capstone_project.data.entities.response

import com.example.capstone_project.data.entities.model.Book
import com.google.gson.annotations.SerializedName

data class AvaibleBookResponse(
    @SerializedName("success") val isSucces: String,
    @SerializedName("payload") val books: List<Book>
)
