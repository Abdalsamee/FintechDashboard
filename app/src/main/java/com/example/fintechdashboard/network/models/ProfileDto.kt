package com.example.fintechdashboard.network.models

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("name") val name: String,
    @SerializedName("balance") val balance: Double,
    @SerializedName("income") val income: Double,
    @SerializedName("expenses") val expenses: Double,
    @SerializedName("savings") val savings: Double,
    @SerializedName("stats") val stats: List<StatDto>
)