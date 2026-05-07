package com.example.fintechdashboard.viewmodel

import com.example.fintechdashboard.domain.models.Profile
import com.example.fintechdashboard.domain.models.Transaction

/**
 * Represents every possible state the Dashboard UI can be in.
 * The UI observes this sealed class and renders accordingly.
 */
sealed class DashboardUiState {

    /** Initial load — show skeletons/shimmer placeholders */
    data object Loading : DashboardUiState()

    /** Both API calls succeeded — all data is ready to render */
    data class Success(
        val profile: Profile,
        val transactions: List<Transaction>
    ) : DashboardUiState()

    /** One or both API calls failed — show an error with a retry option */
    data class Error(
        val message: String
    ) : DashboardUiState()

    /** Success state but user triggered a refresh — data visible + spinner */
    data class Refreshing(
        val profile: Profile,
        val transactions: List<Transaction>
    ) : DashboardUiState()
}