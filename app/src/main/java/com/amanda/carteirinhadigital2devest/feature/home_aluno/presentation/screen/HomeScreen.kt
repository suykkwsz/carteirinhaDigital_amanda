package com.amanda.carteirinhadigital2devest.feature.home_aluno.presentation.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.amanda.carteirinhadigital2devest.app.navigation.Routes
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.amanda.carteirinhadigital2devest.core.ui.AvatarUsuario
import com.amanda.carteirinhadigital2devest.feature.home_aluno.presentation.component.BotaoNavegacao
import com.amanda.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado

@Composable
fun HomeScreen(
    navController: NavController,
    usuarioLogado: UsuarioLogado,
    avatarUri: String? = null,
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
            text = "Aluno",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        AvatarUsuario(tamanho = 82.dp, cor = MaterialTheme.colorScheme.onSurface, fotoUri = avatarUri)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = usuarioLogado.nome,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Text(
            text = usuarioLogado.turma.ifBlank { usuarioLogado.curso },
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.78f),
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
                text = "Carteirinha",
                onClick = { navController.navigate(Routes.Carteirinha.route) },
                modifier = Modifier.fillMaxWidth(0.62f),
                containerColor = AppPalette.purpleButton
            )
            BotaoNavegacao(
                text = "UCs",
                onClick = { navController.navigate(Routes.UCAluno.route) },
                modifier = Modifier.fillMaxWidth(0.62f),
                containerColor = AppPalette.purpleButton
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    CarteirinhaDigital2DEVESTTheme {
        HomeScreen(
            navController = rememberNavController(),
            usuarioLogado = UsuarioLogado(
                id = "1",
                nome = "Amanda Silva",
                matricula = "342344534678",
                perfil = "ALUNO",
                curso = "Desenvolvimento de Sistemas",
                turma = "2DEVEST-A",
                token = "token"
            )
        )
    }
}
