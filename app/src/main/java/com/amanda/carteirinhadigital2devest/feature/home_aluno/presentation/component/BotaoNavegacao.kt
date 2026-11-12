package com.amanda.carteirinhadigital2devest.feature.home_aluno.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette

@Composable
fun BotaoNavegacao(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = AppPalette.purpleButton,
    contentColor: Color = Color.White
) {
    Button(
        modifier = modifier.height(46.dp),
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.SemiBold)
    }
}
