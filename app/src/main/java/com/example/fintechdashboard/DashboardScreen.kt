package com.example.fintechdashboard

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fintechdashboard.component.BalanceCard
import com.example.fintechdashboard.component.HeaderSection
import com.example.fintechdashboard.component.QuickActionsSection
import com.example.fintechdashboard.component.SectionLabel
import com.example.fintechdashboard.component.SpendingStatsGrid
import com.example.fintechdashboard.component.TransactionItem
import com.example.fintechdashboard.data.sampleTransactions

@Composable
fun DashboardScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        item { HeaderSection() }

        item { BalanceCard() }

        item { Spacer(modifier = Modifier.height(4.dp)) }

        item { QuickActionsSection() }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { SpendingStatsGrid() }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            SectionLabel(
                title = "Recent Transactions",
                actionLabel = "See all"
            )
        }

        itemsIndexed(sampleTransactions) { index, transaction ->
            TransactionItem(
                transaction = transaction,
                animationDelay = index * 60
            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }
    }
}