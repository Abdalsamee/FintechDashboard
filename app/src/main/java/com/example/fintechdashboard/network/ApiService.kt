package com.example.fintechdashboard.network

import com.example.fintechdashboard.network.models.ProfileDto
import com.example.fintechdashboard.network.models.TransactionDto
import retrofit2.http.GET

interface ApiService {

    @GET("profile")
    suspend fun getProfile(): List<ProfileDto>

    @GET("transactions")
    suspend fun getTransactions(): List<TransactionDto>
}