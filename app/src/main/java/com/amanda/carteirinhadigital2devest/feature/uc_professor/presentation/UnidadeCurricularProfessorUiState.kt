package com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation

import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.model.UnidadeCurricularProfessor

data class UnidadeCurricularProfessorUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricularProfessor> = emptyList(),
    val errorMessage: String? = null
)
