package com.amanda.carteirinhadigital2devest.feature.uc_professor.data.repository

import com.amanda.carteirinhadigital2devest.core.network.toApiError
import com.amanda.carteirinhadigital2devest.feature.uc_professor.data.remote.service.UnidadeCurricularProfessorApi
import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.model.UnidadeCurricularProfessor
import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.repository.UnidadeCurricularProfessorRepository

class ApiUnidadeCurricularProfessorRepositoryImpl(
    private val api: UnidadeCurricularProfessorApi
) : UnidadeCurricularProfessorRepository {

    override suspend fun listar(): Result<List<UnidadeCurricularProfessor>> =
        runCatching { api.listar().map { it.toDomain() } }
            .recoverCatching { throw it.toApiError("unidades curriculares") }
}
