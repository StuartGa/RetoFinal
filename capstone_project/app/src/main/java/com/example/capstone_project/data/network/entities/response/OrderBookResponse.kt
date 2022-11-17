package com.example.capstoneproject.data.network.entities.response

import com.example.capstoneproject.data.network.entities.model.BidsAskModel
import com.google.gson.annotations.SerializedName

data class OrderBookResponse(
    @SerializedName("success") val isSucces: Boolean,
    @SerializedName("payload") val payload: BidsAskModel

)
