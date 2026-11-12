package com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.repository

import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.model.TurmaProfessor

interface TurmaProfessorRepository {
    suspend fun listar(): Result<List<TurmaProfessor>>
}
