package com.amanda.carteirinhadigital2devest.app.ui

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette
import com.amanda.carteirinhadigital2devest.core.ui.AvatarUsuario
import com.amanda.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppShell(
    title: String,
    usuarioLogado: UsuarioLogado,
    canNavigateBack: Boolean,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    avatarUri: String?,
    onAvatarChange: (String?) -> Unit,
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isProfessor = usuarioLogado.isProfessor
    val gradient = if (isProfessor) AppPalette.professorGradient else AppPalette.studentGradient
    val buttonColor = if (isProfessor) AppPalette.redButton else AppPalette.purpleButton
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let {
            try {
                context.contentResolver.takePersistableUriPermission(
                    it, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: SecurityException) { }
            onAvatarChange(it.toString())
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxHeight(),
                drawerContainerColor = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 24.dp, vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(104.dp)
                            .clip(CircleShape)
                            .background(if (isProfessor) AppPalette.redBottom else AppPalette.purpleBottom),
                        contentAlignment = Alignment.Center
                    ) {
                        AvatarUsuario(tamanho = 76.dp, cor = MaterialTheme.colorScheme.onSurface, fotoUri = avatarUri)
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = usuarioLogado.nome,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Matrícula ${usuarioLogado.matricula}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (isProfessor) "Professor(a)" else "Aluno(a)",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isProfessor) AppPalette.redButton else AppPalette.purpleButton
                    )

                    TextButton(onClick = { imagePicker.launch(arrayOf("image/*")) }) {
                        Text("Alterar foto de perfil", color = buttonColor)
                    }
                    if (avatarUri != null) {
                        TextButton(onClick = { onAvatarChange(null) }) {
                            Text("Remover foto", color = MaterialTheme.colorScheme.error)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (darkTheme) "Tema escuro" else "Tema claro",
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Switch(
                            checked = darkTheme,
                            onCheckedChange = onDarkThemeChange
                        )
                    }
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onLogout
                    ) {
                        Text("Sair", color = buttonColor)
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (darkTheme) SolidColor(MaterialTheme.colorScheme.background) else gradient)
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            if (canNavigateBack) {
                                Text(
                                    text = title,
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    if (canNavigateBack) onNavigateBack()
                                    else scope.launch { drawerState.open() }
                                },
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(buttonColor)
                            ) {
                                Icon(
                                    imageVector = if (canNavigateBack) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu,
                                    contentDescription = if (canNavigateBack) "Voltar" else "Abrir menu",
                                    tint = Color.White
                                )
                            }
                        },
                        actions = {
                            if (canNavigateBack) {
                                IconButton(
                                    onClick = { scope.launch { drawerState.open() } },
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(buttonColor)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Abrir menu",
                                        tint = Color.White
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            navigationIconContentColor = Color.White,
                            actionIconContentColor = Color.White
                        )
                    )
                },
                content = content
            )
        }
    }
}
