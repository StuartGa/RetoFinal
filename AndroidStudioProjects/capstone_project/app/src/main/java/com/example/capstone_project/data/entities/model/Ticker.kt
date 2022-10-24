package com.example.capstone_project.data.entities.model

import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("book") val book: String,
    @SerializedName("volume") val volume: StringSerializedName("low") val low: String
)
