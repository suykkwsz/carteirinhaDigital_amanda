package com.amanda.carteirinhadigital2devest.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AvatarUsuario(
    modifier: Modifier = Modifier,
    tamanho: Dp = 72.dp,
    cor: Color = Color.Black,
    fotoUri: String? = null
) {
    if (fotoUri != null) {
        AsyncImage(
            model = fotoUri,
            contentDescription = "Foto de perfil",
            contentScale = ContentScale.Crop,
            modifier = modifier.size(tamanho).clip(CircleShape)
        )
    } else {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Perfil do usuário",
            tint = cor,
            modifier = modifier.size(tamanho)
        )
    }
}
