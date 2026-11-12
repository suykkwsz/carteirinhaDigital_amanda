package com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amanda.carteirinhadigital2devest.core.ui.ListaApiContent
import com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.UnidadeCurricularProfessorViewModel
import com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.component.UnidadeCurricularProfessorCard

@Composable
fun UnidadeCurricularProfessorScreen(
    viewModel: UnidadeCurricularProfessorViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.carregar() }

    ListaApiContent(
        modifier = modifier,
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        itens = uiState.unidades,
        mensagemVazia = "Nenhuma unidade curricular encontrada.",
        onRetry = viewModel::carregar,
        chave = { it.id }
    ) { unidade ->
        UnidadeCurricularProfessorCard(unidade = unidade)
    }
}
