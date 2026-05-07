package com.example.fintechdashboard.domain.models

import androidx.compose.ui.graphics.Color
import com.example.fintechdashboard.ui.theme.AmberAccent
import com.example.fintechdashboard.ui.theme.BlueAccent
import com.example.fintechdashboard.ui.theme.Emerald
import com.example.fintechdashboard.ui.theme.RedAccent

data class Stat(
    val label: String,
    val value: String,
    val subLabel: String,
    val color: Color,
    val progressFraction: Float
)

// Maps the color string from the API to a real Compose Color
fun String.toStatColor(): Color = when (this.lowercase()) {
    "green"  -> Emerald
    "blue"   -> BlueAccent
    "amber"  -> AmberAccent
    else     -> RedAccent   // "red" + safe fallback
}