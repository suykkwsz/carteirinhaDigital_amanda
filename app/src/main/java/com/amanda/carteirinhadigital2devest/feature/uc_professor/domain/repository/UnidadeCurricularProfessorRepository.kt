package com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.repository

import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.model.UnidadeCurricularProfessor

interface UnidadeCurricularProfessorRepository {
    suspend fun listar(): Result<List<UnidadeCurricularProfessor>>
}
