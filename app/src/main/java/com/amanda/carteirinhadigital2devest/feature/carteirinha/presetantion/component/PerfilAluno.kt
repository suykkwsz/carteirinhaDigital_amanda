package com.amanda.carteirinhadigital2devest.feature.carteirinha.presetantion.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanda.carteirinhadigital2devest.core.ui.AvatarUsuario

@Composable
fun PerfilAluno(
    nome: String,
    matricula: String,
    curso: String,
    modifier: Modifier = Modifier,
    fotoUri: String? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AvatarUsuario(tamanho = 112.dp, cor = Color.Black, fotoUri = fotoUri)
        InfoAluno(
            label = "Nome",
            value = nome,
            fontSizeValue = 18.sp,
            fontWeightValue = FontWeight.SemiBold
        )
        InfoAluno(
            label = "Matrícula",
            value = matricula,
            fontSizeValue = 16.sp,
            fontWeightValue = FontWeight.Medium
        )
        InfoAluno(
            label = "Curso",
            value = curso,
            fontSizeValue = 16.sp,
            fontWeightValue = FontWeight.Normal
        )
    }
}
