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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechdashboard.ui.theme.AmberAccent
import com.example.fintechdashboard.ui.theme.BlueAccent
import com.example.fintechdashboard.ui.theme.CardBorder
import com.example.fintechdashboard.ui.theme.Emerald
import com.example.fintechdashboard.ui.theme.TextSecondary
import kotlin.math.roundToInt

@Composable
fun BalanceCard() {
    val animatedBalance = remember { Animatable(0f) }
    val targetBalance = 12450f

    LaunchedEffect(Unit) {
        animatedBalance.animateTo(
            targetValue = targetBalance,
            animationSpec = tween(durationMillis = 1200)
        )
    }

    val displayBalance = animatedBalance.value.roundToInt()
    val dollars = displayBalance / 100
    val cents = displayBalance % 100

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0D2137),
                        Color(0xFF0A1A2E),
                        Color(0xFF071524)
                    )
                )
            )
    ) {
        // Decorative background circles
        Box(
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.TopEnd)
                .offset(x = 40.dp, y = (-40).dp)
                .clip(CircleShape)
                .background(Emerald.copy(alpha = 0.06f))
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-20).dp, y = 20.dp)
                .clip(CircleShape)
                .background(BlueAccent.copy(alpha = 0.07f))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Balance",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "$",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Emerald,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "%,d".format(dollars),
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = (-1).sp
                        )
                        Text(
                            text = ".%02d".format(cents),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }
                }

                // Card chip visual
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(AmberAccent.copy(alpha = 0.8f), AmberAccent.copy(alpha = 0.4f))
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BalanceStat(label = "Income", amount = "+\$5,180", color = Emerald)
                BalanceStat(label = "Expenses", amount = "-\$522", color = Color(0xFFFF4D6A))
                BalanceStat(label = "Savings", amount = "\$3,200", color = BlueAccent)
            }
        }
    }
}

@Composable
private fun BalanceStat(label: String, amount: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = amount,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = color
        )
    }
}