package com.example.capstone_project.data.network.entities.response

import com.example.capstone_project.data.network.entities.model.Ask
import com.example.capstone_project.data.network.entities.model.Bid
import com.example.capstone_project.data.network.entities.model.BidsAskModel
import com.google.gson.annotations.SerializedName

data class OrderBookResponse(
    @SerializedName("success") val isSucces: Boolean,
    @SerializedName("payload") val payload: BidsAskModel

)
