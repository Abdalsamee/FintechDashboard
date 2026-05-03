package com.example.fintechdashboard.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCard
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material.icons.outlined.CallReceived
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.fintechdashboard.ui.theme.BlueAccent
import com.example.fintechdashboard.ui.theme.Emerald
import com.example.fintechdashboard.ui.theme.AmberAccent
import com.example.fintechdashboard.ui.theme.RedAccent
import kotlinx.coroutines.launch

data class QuickAction(
    val label: String,
    val icon: ImageVector,
    val tint: Color,
    val bg: Color
)

@Composable
fun QuickActionsSection() {
    val actions = listOf(
        QuickAction("Send", Icons.Outlined.ArrowOutward, Emerald, Emerald.copy(alpha = 0.12f)),
        QuickAction("Receive", Icons.Outlined.CallReceived, BlueAccent, BlueAccent.copy(alpha = 0.12f)),
        QuickAction("Pay", Icons.Outlined.AddCard, AmberAccent, AmberAccent.copy(alpha = 0.12f)),
        QuickAction("Scan", Icons.Outlined.QrCode2, RedAccent, RedAccent.copy(alpha = 0.12f))
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        actions.forEach { action ->
            QuickActionButton(action)
        }
    }
}

@Composable
fun QuickActionButton(action: QuickAction) {
    val scale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .scale(scale.value)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                scope.launch {
                    scale.animateTo(0.88f, animationSpec = spring(stiffness = 700f))
                    scale.animateTo(1f, animationSpec = spring(stiffness = 400f))
                }
            }
    ) {
        // Icon circle
        Column(
            modifier = Modifier
                .size(58.dp)
                .clip(CircleShape)
                .background(action.bg),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = action.label,
                tint = action.tint,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = action.label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}