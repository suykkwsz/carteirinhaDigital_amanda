package com.amanda.carteirinhadigital2devest.feature.home_professor.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.amanda.carteirinhadigital2devest.core.ui.AvatarUsuario
import com.amanda.carteirinhadigital2devest.feature.home_aluno.presentation.component.BotaoNavegacao
import com.amanda.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado

@Composable
fun HomeProfessorScreen(
    usuarioLogado: UsuarioLogado,
    onNavigateToTurmas: () -> Unit,
    onNavigateToUnidadesCurriculares: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Professor",
            color = AppPalette.ink,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        AvatarUsuario(tamanho = 82.dp, cor = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = usuarioLogado.nome,
            color = AppPalette.ink,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Matrícula ${usuarioLogado.matricula}",
            color = AppPalette.ink.copy(alpha = 0.78f),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(34.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BotaoNavegacao(
                text = "Turmas",
                onClick = onNavigateToTurmas,
                modifier = Modifier.fillMaxWidth(0.62f),
                containerColor = AppPalette.redButton
            )
            BotaoNavegacao(
                text = "UCs",
                onClick = onNavigateToUnidadesCurriculares,
                modifier = Modifier.fillMaxWidth(0.62f),
                containerColor = AppPalette.redButton
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeProfessorScreenPreview() {
    CarteirinhaDigital2DEVESTTheme {
        HomeProfessorScreen(
            usuarioLogado = UsuarioLogado(
                id = "3",
                nome = "Amanda",
                matricula = "PROF20260001",
                perfil = "PROFESSOR",
                curso = "",
                turma = "",
                token = "token"
            ),
            onNavigateToTurmas = {},
            onNavigateToUnidadesCurriculares = {}
        )
    }
}
