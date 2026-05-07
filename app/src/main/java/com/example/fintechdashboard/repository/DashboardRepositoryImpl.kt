package com.example.fintechdashboard.repository

import com.example.fintechdashboard.domain.models.Profile
import com.example.fintechdashboard.domain.models.Stat
import com.example.fintechdashboard.domain.models.Transaction
import com.example.fintechdashboard.domain.models.toStatColor
import com.example.fintechdashboard.domain.models.toTransactionEmoji
import com.example.fintechdashboard.network.ApiService
import com.example.fintechdashboard.network.models.ProfileDto
import com.example.fintechdashboard.network.models.StatDto
import com.example.fintechdashboard.network.models.TransactionDto

class DashboardRepositoryImpl(
    private val apiService: ApiService
) : DashboardRepository {

    override suspend fun getProfile(): Result<Profile> = runCatching {
        val profileList = apiService.getProfile()
        profileList.firstOrNull()?.toDomain()
            ?: error("Profile data is empty.")
    }

    override suspend fun getTransactions(): Result<List<Transaction>> = runCatching {
        apiService.getTransactions().map { it.toDomain() }
    }

    // --- Mapping functions ---

    private fun ProfileDto.toDomain() = Profile(
        name = name,
        balance = balance,
        income = income,
        expenses = expenses,
        savings = savings,
        stats = stats.map { it.toDomain() }
    )

    private fun StatDto.toDomain() = Stat(
        label = label,
        value = value,
        subLabel = subLabel,
        color = color.toStatColor(),
        progressFraction = progressFraction.coerceIn(0f, 1f)
    )

    private fun TransactionDto.toDomain() = Transaction(
        title = title,
        amount = amount,
        isCredit = type == "income",
        date = date,
        iconEmoji = icon.toTransactionEmoji()
    )
}