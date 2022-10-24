package com.example.capstone_project.data.entities.response

import com.example.capstone_project.data.entities.model.Ask
import com.google.gson.annotations.SerializedName

data class AskResponse(
    @SerializedName("success") val isSucces: Boolean,
    @SerializedName("asks") val asks: List<Ask>
)
