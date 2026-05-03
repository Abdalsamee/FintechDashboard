package com.example.fintechdashboard.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechdashboard.data.Transaction
import com.example.fintechdashboard.ui.theme.Emerald
import com.example.fintechdashboard.ui.theme.RedAccent
import com.example.fintechdashboard.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun TransactionItem(transaction: Transaction, animationDelay: Int = 0) {
    val alpha = remember { Animatable(0f) }
    val slideY = remember { Animatable(16f) }

    LaunchedEffect(Unit) {
        delay(animationDelay.toLong())
        alpha.animateTo(1f, animationSpec = tween(350))
        slideY.animateTo(0f, animationSpec = tween(350))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                this.alpha = alpha.value
                translationY = slideY.value
            }
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon circle
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = transaction.iconEmoji,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = transaction.category,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = transaction.amount,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isCredit) Emerald else RedAccent
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = transaction.date,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}