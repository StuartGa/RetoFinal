package com.example.capstone_project.data.network.entities.model

import com.google.gson.annotations.SerializedName

data class BidsAskModel(
    @SerializedName("asks") val asks: List<Ask>,
    @SerializedName("bids") val bid: List<Bid>
)
