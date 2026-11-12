package com.amanda.carteirinhadigital2devest.app.di

import com.amanda.carteirinhadigital2devest.core.auth.SessionTokenStore
import com.amanda.carteirinhadigital2devest.feature.login.domain.repository.LoginRepository
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.repository.TurmaProfessorRepository
import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.repository.UnidadeCurricularProfessorRepository
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository

interface AppContainer {
    val sessionTokenStore : SessionTokenStore

    val loginRepository : LoginRepository

    val unidadeCurricularRepository : UnidadeCurricularRepository

    val turmaProfessorRepository : TurmaProfessorRepository

    val unidadeCurricularProfessorRepository : UnidadeCurricularProfessorRepository
}