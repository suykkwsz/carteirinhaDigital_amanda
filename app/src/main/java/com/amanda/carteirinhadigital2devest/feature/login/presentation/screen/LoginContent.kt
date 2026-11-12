package com.amanda.carteirinhadigital2devest.feature.login.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanda.carteirinhadigital2devest.R
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.amanda.carteirinhadigital2devest.feature.login.presentation.LoginEvent
import com.amanda.carteirinhadigital2devest.feature.login.presentation.LoginUiState

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEvent: (LoginEvent) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppPalette.studentGradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo SENAI",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(width = 250.dp, height = 76.dp)
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Color(0x4425093D),
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.22f))
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 26.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Acesse sua conta",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Entre com seu usuário e senha",
                        color = Color.White.copy(alpha = 0.82f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

                    OutlinedTextField(
                        value = uiState.usuario,
                        onValueChange = { onEvent(LoginEvent.OnUsuarioChange(it)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = uiState.credentialError,
                        label = { Text("Usuário") },
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF9F6FC),
                            unfocusedContainerColor = Color(0xFFF9F6FC),
                            focusedTextColor = AppPalette.ink,
                            unfocusedTextColor = AppPalette.ink,
                            focusedLabelColor = AppPalette.purpleButton,
                            unfocusedLabelColor = Color(0xFF665B6D),
                            focusedBorderColor = Color(0xFFD6B7ED),
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = MaterialTheme.colorScheme.error
                        )
                    )

                    OutlinedTextField(
                        value = uiState.senha,
                        onValueChange = { onEvent(LoginEvent.OnSenhaChange(it)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = uiState.credentialError,
                        label = { Text("Senha") },
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF9F6FC),
                            unfocusedContainerColor = Color(0xFFF9F6FC),
                            focusedTextColor = AppPalette.ink,
                            unfocusedTextColor = AppPalette.ink,
                            focusedLabelColor = AppPalette.purpleButton,
                            unfocusedLabelColor = Color(0xFF665B6D),
                            focusedBorderColor = Color(0xFFD6B7ED),
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = MaterialTheme.colorScheme.error
                        )
                    )

                    uiState.errorMessage?.let { error ->
                        Text(
                            text = error,
                            color = Color(0xFFFFD8D8),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Button(
                        onClick = { onEvent(LoginEvent.OnEntrarClick) },
                        enabled = !uiState.isLoading,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppPalette.purpleButton,
                            contentColor = Color.White,
                            disabledContainerColor = AppPalette.purpleButton.copy(alpha = 0.6f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        if (uiState.isLoading) {
                            LinearProgressIndicator(
                                modifier = Modifier.fillMaxWidth(0.55f),
                                color = Color.White,
                                trackColor = Color.White.copy(alpha = 0.3f)
                            )
                        } else {
                            Text("Entrar", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "Carteirinha Digital • 2DEVEST",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentPreview() {
    CarteirinhaDigital2DEVESTTheme {
        LoginContent(
            modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState(usuario = "amanda", senha = "123"),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, name = "Login Error")
@Composable
private fun LoginContentErrorPreview() {
    CarteirinhaDigital2DEVESTTheme {
        LoginContent(
            modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState(
                usuario = "amanda",
                senha = "000",
                errorMessage = "Login ou senha inválidos",
                credentialError = true
            ),
            onEvent = {}
        )
    }
}
