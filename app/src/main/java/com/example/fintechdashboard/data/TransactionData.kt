package com.example.fintechdashboard.data

data class Transaction(
    val title: String,
    val category: String,
    val amount: String,
    val date: String,
    val isCredit: Boolean,
    val iconEmoji: String
)

val sampleTransactions = listOf(
    Transaction("Amazon", "Shopping", "-\$120.00", "Today, 2:34 PM", false, "🛒"),
    Transaction("Salary", "Income", "+\$4,200.00", "Today, 9:00 AM", true, "💼"),
    Transaction("Netflix", "Streaming", "-\$15.99", "Yesterday", false, "🎬"),
    Transaction("Starbucks", "Food & Drink", "-\$6.50", "Yesterday", false, "☕"),
    Transaction("Freelance", "Income", "+\$850.00", "May 1", true, "💻"),
    Transaction("Spotify", "Streaming", "-\$9.99", "Apr 30", false, "🎵"),
    Transaction("Uber", "Transport", "-\$22.40", "Apr 30", false, "🚗"),
    Transaction("Apple Store", "Technology", "-\$299.00", "Apr 29", false, "📱"),
    Transaction("Dividend", "Income", "+\$130.00", "Apr 28", true, "📈"),
    Transaction("Gym", "Health", "-\$49.00", "Apr 28", false, "🏋️"),
)