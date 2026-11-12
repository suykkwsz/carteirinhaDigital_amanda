package com.amanda.carteirinhadigital2devest.feature.login.data.repository

import com.amanda.carteirinhadigital2devest.feature.login.domain.repository.LoginRepository

import com.amanda.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.delay

class FakeAuthRepository : LoginRepository {
    override suspend fun login(
        login: String,
        senha: String
    ): Result<UsuarioLogado> {

        delay(1500)

        return if (login.equals("amanda", ignoreCase = true) && senha == "2202") {
            Result.success(
                UsuarioLogado(
                    id = "1",
                    nome = "Amanda Silva",
                    matricula = "342344534678",
                    perfil = "ALUNO",
                    curso = "Desenvolvimento de Sistemas",
                    turma = "2DEVEST-A",
                    token = "token-fake-para-aula"
                )
            )
        } else {
            Result.failure(IllegalArgumentException("Login ou senha inválidos"))
        }
    }
}