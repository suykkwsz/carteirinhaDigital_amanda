package com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation

import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.model.TurmaProfessor

data class TurmaProfessorUiState(
    val isLoading: Boolean = false,
    val turmas: List<TurmaProfessor> = emptyList(),
    val errorMessage: String? = null
)
