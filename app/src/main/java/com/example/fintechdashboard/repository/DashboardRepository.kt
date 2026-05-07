package com.example.fintechdashboard.repository

import com.example.fintechdashboard.domain.models.Profile
import com.example.fintechdashboard.domain.models.Transaction

interface DashboardRepository {
    suspend fun getProfile(): Result<Profile>
    suspend fun getTransactions(): Result<List<Transaction>>
}