package com.amanda.carteirinhadigital2devest.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amanda.carteirinhadigital2devest.app.di.AppContainer
import com.amanda.carteirinhadigital2devest.app.session.SessionViewModel
import com.amanda.carteirinhadigital2devest.app.session.SessionViewModelFactory
import com.amanda.carteirinhadigital2devest.app.ui.AppShell
import com.amanda.carteirinhadigital2devest.feature.carteirinha.presetantion.screen.CarteirinhaScreen
import com.amanda.carteirinhadigital2devest.feature.home_aluno.presentation.screen.HomeScreen
import com.amanda.carteirinhadigital2devest.feature.home_professor.presentation.screen.HomeProfessorScreen
import com.amanda.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import com.amanda.carteirinhadigital2devest.feature.login.presentation.LoginViewModel
import com.amanda.carteirinhadigital2devest.feature.login.presentation.factory.LoginViewModelFactory
import com.amanda.carteirinhadigital2devest.feature.login.presentation.screen.LoginScreen
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.TurmaProfessorViewModel
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.factory.TurmaProfessorViewModelFactory
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.screen.TurmaProfessorScreen
import com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.UnidadeCurricularProfessorViewModel
import com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.factory.UnidadeCurricularProfessorViewModelFactory
import com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.screen.UnidadeCurricularProfessorScreen
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    avatarUri: String?,
    onAvatarChange: (String?) -> Unit,
    container: AppContainer
) {
    val sessionFactory = remember(container.sessionTokenStore) {
        SessionViewModelFactory(
            sessionTokenStore = container.sessionTokenStore
        )
    }

    val sessionViewModel: SessionViewModel = viewModel(factory = sessionFactory)
    val usuarioLogado by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()
    val usuario = usuarioLogado

    fun logout() {
        sessionViewModel.limparSessao()
        navController.navigate(Routes.Login.route) {
            // Limpa toda a pilha, seja do perfil Aluno ou Professor.
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    /** Casca comum (TopAppBar + drawer) usada por todas as telas autenticadas. */
    @Composable
    fun TelaAutenticada(
        title: String,
        canNavigateBack: Boolean,
        content: @Composable (UsuarioLogado, Modifier) -> Unit
    ) {
        if (usuario == null) {
            RedirecionarParaLogin(navController)
        } else {
            AppShell(
                title = title,
                usuarioLogado = usuario,
                canNavigateBack = canNavigateBack,
                darkTheme = darkTheme,
                onDarkThemeChange = onDarkThemeChange,
                avatarUri = avatarUri,
                onAvatarChange = onAvatarChange,
                onNavigateBack = { navController.popBackStack() },
                onLogout = { logout() }
            ) { innerPadding ->
                content(
                    usuario,
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            val loginFactory = remember(
                container.loginRepository
            ) {
                LoginViewModelFactory(
                    repository = container.loginRepository
                )
            }

            val loginViewModel: LoginViewModel = viewModel(factory = loginFactory)

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSucesso = { usuarioAutenticado ->
                    sessionViewModel.setUsuarioLogado(usuarioAutenticado)

                    // O perfil devolvido pela API define o fluxo de navegação.
                    val destino = if (usuarioAutenticado.isProfessor) {
                        Routes.HomeProfessor.route
                    } else {
                        Routes.HomeAluno.route
                    }

                    navController.navigate(destino) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        // ---------------- Perfil Aluno ----------------

        composable(Routes.HomeAluno.route) {
            TelaAutenticada(title = "Início", canNavigateBack = false) { user, modifier ->
                HomeScreen(
                    navController = navController,
                    usuarioLogado = user,
                    avatarUri = avatarUri,
                    modifier = modifier
                )
            }
        }

        composable(Routes.Carteirinha.route) {
            TelaAutenticada(title = "Carteirinha", canNavigateBack = true) { user, modifier ->
                CarteirinhaScreen(
                    usuarioLogado = user,
                    avatarUri = avatarUri,
                    modifier = modifier
                )
            }
        }

        composable(Routes.UCAluno.route) {
            val ucFactory = remember(container.unidadeCurricularRepository) {
                UnidadeCurricularViewModelFactory(
                    repository = container.unidadeCurricularRepository
                )
            }
            val ucViewModel: UnidadeCurricularViewModel = viewModel(factory = ucFactory)

            TelaAutenticada(
                title = "Unidades Curriculares",
                canNavigateBack = true
            ) { _, modifier ->
                UnidadeCurricularScreen(
                    viewModel = ucViewModel,
                    modifier = modifier
                )
            }
        }

        // ---------------- Perfil Professor ----------------

        composable(Routes.HomeProfessor.route) {
            TelaAutenticada(title = "Início", canNavigateBack = false) { user, modifier ->
                HomeProfessorScreen(
                    usuarioLogado = user,
                    onNavigateToTurmas = {
                        navController.navigate(Routes.TurmasProfessor.route)
                    },
                    onNavigateToUnidadesCurriculares = {
                        navController.navigate(Routes.UCProfessor.route)
                    },
                    modifier = modifier
                )
            }
        }

        composable(Routes.TurmasProfessor.route) {
            val turmasFactory = remember(container.turmaProfessorRepository) {
                TurmaProfessorViewModelFactory(
                    repository = container.turmaProfessorRepository
                )
            }
            val turmasViewModel: TurmaProfessorViewModel = viewModel(factory = turmasFactory)

            TelaAutenticada(title = "Turmas", canNavigateBack = true) { _, modifier ->
                TurmaProfessorScreen(
                    viewModel = turmasViewModel,
                    modifier = modifier
                )
            }
        }

        composable(Routes.UCProfessor.route) {
            val ucProfessorFactory = remember(container.unidadeCurricularProfessorRepository) {
                UnidadeCurricularProfessorViewModelFactory(
                    repository = container.unidadeCurricularProfessorRepository
                )
            }
            val ucProfessorViewModel: UnidadeCurricularProfessorViewModel =
                viewModel(factory = ucProfessorFactory)

            TelaAutenticada(
                title = "Unidades Curriculares",
                canNavigateBack = true
            ) { _, modifier ->
                UnidadeCurricularProfessorScreen(
                    viewModel = ucProfessorViewModel,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun RedirecionarParaLogin(
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        navController.navigate(Routes.Login.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}
