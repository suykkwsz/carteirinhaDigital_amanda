package com.amanda.carteirinhadigital2devest.feature.turmas_professor.data.repository

import com.amanda.carteirinhadigital2devest.core.network.toApiError
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.data.remote.service.TurmaProfessorApi
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.model.TurmaProfessor
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.repository.TurmaProfessorRepository

class ApiTurmaProfessorRepositoryImpl(
    private val api: TurmaProfessorApi
) : TurmaProfessorRepository {

    override suspend fun listar(): Result<List<TurmaProfessor>> =
        runCatching { api.listar().map { it.toDomain() } }
            .recoverCatching { throw it.toApiError("turmas") }
}
