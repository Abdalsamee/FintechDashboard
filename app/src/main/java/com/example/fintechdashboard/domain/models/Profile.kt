package com.example.fintechdashboard.domain.models

data class Profile(
    val name: String,
    val balance: Double,
    val income: Double,
    val expenses: Double,
    val savings: Double,
    val stats: List<Stat>
)