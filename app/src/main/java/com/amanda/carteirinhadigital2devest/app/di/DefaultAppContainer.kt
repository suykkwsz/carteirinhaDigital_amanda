package com.amanda.carteirinhadigital2devest.app.di

import com.amanda.carteirinhadigital2devest.core.auth.SessionTokenStore
import com.amanda.carteirinhadigital2devest.core.network.NetworkClient
import com.amanda.carteirinhadigital2devest.feature.login.data.remote.service.AuthApi
import com.amanda.carteirinhadigital2devest.feature.login.data.repository.ApiLoginRepositoryImpl
import com.amanda.carteirinhadigital2devest.feature.login.data.repository.FakeAuthRepository
import com.amanda.carteirinhadigital2devest.feature.login.domain.repository.LoginRepository
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.data.remote.service.TurmaProfessorApi
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.data.repository.ApiTurmaProfessorRepositoryImpl
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.repository.TurmaProfessorRepository
import com.amanda.carteirinhadigital2devest.feature.uc_professor.data.remote.service.UnidadeCurricularProfessorApi
import com.amanda.carteirinhadigital2devest.feature.uc_professor.data.repository.ApiUnidadeCurricularProfessorRepositoryImpl
import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.repository.UnidadeCurricularProfessorRepository
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository

class DefaultAppContainer : AppContainer {
    override val sessionTokenStore : SessionTokenStore = SessionTokenStore()
    private val networkClient =
        NetworkClient(
            baseUrl = BASE_URL,
            sessionTokenStore = sessionTokenStore
        )

    private val authApi : AuthApi by lazy {
        networkClient.createPublic(
            AuthApi::class.java
        )
    }
    private val unidadeCurricularApi : UnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(
                UnidadeCurricularApi::class.java
            )
    }
    private val turmaProfessorApi : TurmaProfessorApi by lazy {
        networkClient.createAuthenticated(
            TurmaProfessorApi::class.java
        )
    }
    private val unidadeCurricularProfessorApi : UnidadeCurricularProfessorApi by lazy {
        networkClient.createAuthenticated(
            UnidadeCurricularProfessorApi::class.java
        )
    }
    override val loginRepository : LoginRepository by lazy {
        if (USE_FAKE_LOGIN_REPOSITORY ) {
            FakeAuthRepository()
        } else {
            ApiLoginRepositoryImpl(
                api =authApi
            )
        }
    }
    override val unidadeCurricularRepository : UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(
            api = unidadeCurricularApi
        )
    }
    override val turmaProfessorRepository : TurmaProfessorRepository by lazy {
        ApiTurmaProfessorRepositoryImpl(
            api = turmaProfessorApi
        )
    }
    override val unidadeCurricularProfessorRepository : UnidadeCurricularProfessorRepository by lazy {
        ApiUnidadeCurricularProfessorRepositoryImpl(
            api = unidadeCurricularProfessorApi
        )
    }
    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
        private const val USE_FAKE_LOGIN_REPOSITORY = false
    }
}