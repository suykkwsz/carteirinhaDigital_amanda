package com.amanda.carteirinhadigital2devest.feature.carteirinha.presetantion.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette
import com.amanda.carteirinhadigital2devest.core.ui.AvatarUsuario
import com.amanda.carteirinhadigital2devest.feature.carteirinha.presetantion.component.QrCode
import com.amanda.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado

@Composable
fun CarteirinhaScreen(
    usuarioLogado: UsuarioLogado,
    avatarUri: String? = null,
    modifier: Modifier = Modifier
) {
    // No tema escuro o fundo é escuro; usa texto claro para não sumir.
    val textoSecundario = if (MaterialTheme.colorScheme.background.luminance() < 0.5f) {
        Color.White
    } else {
        AppPalette.ink
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CARTEIRINHA DIGITAL",
                color = Color.White.copy(alpha = 0.9f),
                letterSpacing = 2.sp,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .size(86.dp)
                    .background(Color.White.copy(alpha = 0.18f), RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                AvatarUsuario(tamanho = 72.dp, cor = Color.Black, fotoUri = avatarUri)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = usuarioLogado.nome,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = usuarioLogado.turma.ifBlank { usuarioLogado.curso },
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                QrCode(
                    conteudo = usuarioLogado.matricula,
                    modifier = Modifier
                        .padding(12.dp)
                        .size(204.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Matrícula ${usuarioLogado.matricula}",
                color = textoSecundario,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Apresente este QR Code para identificação",
                color = textoSecundario.copy(alpha = 0.82f),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}
