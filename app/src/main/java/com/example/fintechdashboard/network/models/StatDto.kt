package com.example.fintechdashboard.network.models

import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("label") val label: String,
    @SerializedName("value") val value: String,
    @SerializedName("subLabel") val subLabel: String,
    @SerializedName("color") val color: String,
    @SerializedName("progressFraction") val progressFraction: Float
)