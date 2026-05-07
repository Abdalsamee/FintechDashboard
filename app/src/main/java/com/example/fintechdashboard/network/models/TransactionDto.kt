package com.example.fintechdashboard.network.models

import com.google.gson.annotations.SerializedName

data class TransactionDto(
    @SerializedName("title") val title: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("type") val type: String,
    @SerializedName("date") val date: String,
    @SerializedName("icon") val icon: String
)