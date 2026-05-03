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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechdashboard.ui.theme.AmberAccent
import com.example.fintechdashboard.ui.theme.BlueAccent
import com.example.fintechdashboard.ui.theme.Emerald
import com.example.fintechdashboard.ui.theme.RedAccent
import com.example.fintechdashboard.ui.theme.TextSecondary
import kotlinx.coroutines.delay

data class StatItem(
    val label: String,
    val value: String,
    val subLabel: String,
    val color: Color,
    val progressFraction: Float
)

@Composable
fun SpendingStatsGrid() {
    val stats = listOf(
        StatItem("Spent", "\$522", "of \$1,200 budget", RedAccent, 0.44f),
        StatItem("Saved", "\$3,200", "this month", Emerald, 0.72f),
        StatItem("Investments", "\$8,400", "+4.2% this week", BlueAccent, 0.68f),
        StatItem("Pending", "\$340", "3 transactions", AmberAccent, 0.28f)
    )

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(stat = stats[0], animDelay = 0, modifier = Modifier.weight(1f))
            StatCard(stat = stats[1], animDelay = 80, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(stat = stats[2], animDelay = 160, modifier = Modifier.weight(1f))
            StatCard(stat = stats[3], animDelay = 240, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StatCard(stat: StatItem, animDelay: Int, modifier: Modifier = Modifier) {
    val alpha = remember { Animatable(0f) }
    val progressAnim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(animDelay.toLong())
        alpha.animateTo(1f, animationSpec = tween(400))
        progressAnim.animateTo(stat.progressFraction, animationSpec = tween(900))
    }

    Box(
        modifier = modifier
            .alpha(alpha.value)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Column {
            // Color dot indicator
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(stat.color)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stat.value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = stat.label,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Thin progress bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(stat.color.copy(alpha = 0.12f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progressAnim.value)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(stat.color)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stat.subLabel,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}