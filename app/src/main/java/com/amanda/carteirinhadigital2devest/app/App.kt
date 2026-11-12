package com.amanda.carteirinhadigital2devest.app

import androidx.compose.foundation.isSystemInDarkTheme
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.amanda.carteirinhadigital2devest.app.di.AppContainer
import com.amanda.carteirinhadigital2devest.app.navigation.AppNavHost
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme

@Composable
fun App(container: AppContainer) {

    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences("preferencias_app", Context.MODE_PRIVATE) }
    val systemDarkTheme = isSystemInDarkTheme()
    var darkTheme by rememberSaveable { mutableStateOf(preferences.getBoolean("tema_escuro", systemDarkTheme)) }
    var avatarUri by rememberSaveable { mutableStateOf(preferences.getString("foto_perfil_uri", null)) }

    CarteirinhaDigital2DEVESTTheme(
        darkTheme = darkTheme
    ) {
        val navController = rememberNavController()

        AppNavHost(
            navController = navController,
            darkTheme = darkTheme,
            onDarkThemeChange = {
                darkTheme = it
                preferences.edit().putBoolean("tema_escuro", it).apply()
            },
            avatarUri = avatarUri,
            onAvatarChange = {
                avatarUri = it
                preferences.edit().putString("foto_perfil_uri", it).apply()
            },
            container = container
        )
    }
}