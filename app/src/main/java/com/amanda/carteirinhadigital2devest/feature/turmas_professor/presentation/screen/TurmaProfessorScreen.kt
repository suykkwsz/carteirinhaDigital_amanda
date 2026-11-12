package com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amanda.carteirinhadigital2devest.core.ui.ListaApiContent
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.TurmaProfessorViewModel
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.component.TurmaProfessorCard

@Composable
fun TurmaProfessorScreen(
    viewModel: TurmaProfessorViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.carregar() }

    ListaApiContent(
        modifier = modifier,
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        itens = uiState.turmas,
        mensagemVazia = "Nenhuma turma vinculada ao professor.",
        onRetry = viewModel::carregar,
        chave = { it.id }
    ) { turma ->
        TurmaProfessorCard(turma = turma)
    }
}
