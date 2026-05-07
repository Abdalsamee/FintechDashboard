package com.example.fintechdashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fintechdashboard.network.RetrofitClient
import com.example.fintechdashboard.repository.DashboardRepository
import com.example.fintechdashboard.repository.DashboardRepositoryImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: DashboardRepository
) : ViewModel() {

    // Backing property — mutable, private to this class only
    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)

    // Exposed to the UI — read-only
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    /**
     * Initial load. Fires both API calls in parallel.
     * Sets Loading state while fetching.
     */
    fun loadDashboard() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading

            // Fire both requests concurrently
            val profileDeferred = async { repository.getProfile() }
            val transactionsDeferred = async { repository.getTransactions() }

            val profileResult = profileDeferred.await()
            val transactionsResult = transactionsDeferred.await()

            _uiState.value = when {
                profileResult.isSuccess && transactionsResult.isSuccess -> {
                    DashboardUiState.Success(
                        profile = profileResult.getOrThrow(),
                        transactions = transactionsResult.getOrThrow()
                    )
                }
                profileResult.isFailure -> {
                    DashboardUiState.Error(
                        message = profileResult.exceptionOrNull()?.message
                            ?: "Failed to load profile."
                    )
                }
                else -> {
                    DashboardUiState.Error(
                        message = transactionsResult.exceptionOrNull()?.message
                            ?: "Failed to load transactions."
                    )
                }
            }
        }
    }

    /**
     * Pull-to-refresh. Keeps existing data visible while re-fetching.
     * Only triggers if currently in a Success state.
     */
    fun refresh() {
        val current = _uiState.value
        if (current !is DashboardUiState.Success) return

        viewModelScope.launch {
            _uiState.value = DashboardUiState.Refreshing(
                profile = current.profile,
                transactions = current.transactions
            )

            val profileDeferred = async { repository.getProfile() }
            val transactionsDeferred = async { repository.getTransactions() }

            val profileResult = profileDeferred.await()
            val transactionsResult = transactionsDeferred.await()

            _uiState.value = when {
                profileResult.isSuccess && transactionsResult.isSuccess -> {
                    DashboardUiState.Success(
                        profile = profileResult.getOrThrow(),
                        transactions = transactionsResult.getOrThrow()
                    )
                }
                else -> {
                    // On refresh failure, restore previous data + show error message
                    DashboardUiState.Error(
                        message = "Refresh failed. Please try again."
                    )
                }
            }
        }
    }

    /**
     * Manual factory — creates the ViewModel with its repository dependency.
     * Replaced by Hilt in a production setup.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                val repository = DashboardRepositoryImpl(RetrofitClient.apiService)
                return DashboardViewModel(repository) as T
            }
        }
    }
}