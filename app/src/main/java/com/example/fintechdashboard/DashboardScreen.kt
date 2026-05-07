package com.example.fintechdashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fintechdashboard.component.BalanceCard
import com.example.fintechdashboard.component.DashboardSkeleton
import com.example.fintechdashboard.component.ErrorState
import com.example.fintechdashboard.component.HeaderSection
import com.example.fintechdashboard.component.QuickActionsSection
import com.example.fintechdashboard.component.SectionLabel
import com.example.fintechdashboard.component.SpendingStatsGrid
import com.example.fintechdashboard.component.TransactionItem
import com.example.fintechdashboard.viewmodel.DashboardUiState
import com.example.fintechdashboard.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {

        is DashboardUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                DashboardSkeleton()
            }
        }

        is DashboardUiState.Error -> {
            ErrorState(
                message = state.message,
                onRetry = { viewModel.loadDashboard() }
            )
        }

        is DashboardUiState.Success,
        is DashboardUiState.Refreshing -> {
            val profile = if (state is DashboardUiState.Success) state.profile
            else (state as DashboardUiState.Refreshing).profile
            val transactions = if (state is DashboardUiState.Success) state.transactions
            else (state as DashboardUiState.Refreshing).transactions
            val isRefreshing = state is DashboardUiState.Refreshing

            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = { viewModel.refresh() },
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    item { HeaderSection(name = profile.name) }

                    item {
                        BalanceCard(
                            balance = profile.balance,
                            income = profile.income,
                            expenses = profile.expenses,
                            savings = profile.savings
                        )
                    }

                    item { Spacer(modifier = Modifier.height(4.dp)) }

                    item { QuickActionsSection() }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item { SpendingStatsGrid(stats = profile.stats) }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        SectionLabel(
                            title = "Recent Transactions",
                            actionLabel = "See all"
                        )
                    }

                    itemsIndexed(transactions) { index, transaction ->
                        TransactionItem(
                            transaction = transaction,
                            animationDelay = index * 60
                        )
                    }

                    item { Spacer(modifier = Modifier.height(32.dp)) }
                }
            }
        }
    }
}