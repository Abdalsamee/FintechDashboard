package com.example.fintechdashboard.domain.models

data class Transaction(
    val title: String,
    val amount: String,
    val isCredit: Boolean,
    val date: String,
    val iconEmoji: String
)

// Maps the icon slug from the API to a display emoji
fun String.toTransactionEmoji(): String = when (this.lowercase()) {
    "netflix"   -> "🎬"
    "salary"    -> "💼"
    "shopping"  -> "🛒"
    "transport" -> "🚗"
    "food"      -> "☕"
    "health"    -> "🏋️"
    "music"     -> "🎵"
    "tech"      -> "📱"
    "dividend"  -> "📈"
    else        -> "💳"   // safe fallback for unknown slugs
}