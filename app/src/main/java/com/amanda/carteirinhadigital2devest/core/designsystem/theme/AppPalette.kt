package com.amanda.carteirinhadigital2devest.core.designsystem.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/** Cores da identidade visual dos perfis da Carteirinha Digital. */
object AppPalette {
    val purpleTop = Color(0xFF4B176F)
    val purpleMiddle = Color(0xFF713C94)
    val purpleBottom = Color(0xFF987BAA)
    val purpleButton = Color(0xFF2A0645)

    val redTop = Color(0xFF9E2429)
    val redMiddle = Color(0xFFB9373D)
    val redBottom = Color(0xFFD16A6D)
    val redButton = Color(0xFF591519)

    val ink = Color(0xFF130D18)
    val softWhite = Color(0xFFF9F6FC)

    val studentGradient = Brush.verticalGradient(
        colors = listOf(purpleTop, purpleMiddle, purpleBottom)
    )
    val professorGradient = Brush.verticalGradient(
        colors = listOf(redTop, redMiddle, redBottom)
    )
}
