package com.amanda.carteirinhadigital2devest.feature.login.domain.model

data class UsuarioLogado(
    val id: String,
    val nome: String,
    val matricula: String,
    val perfil: String,
    val curso: String,
    val turma: String,
    val token: String
) {
    val isProfessor: Boolean
        get() = perfil.equals("PROFESSOR", ignoreCase = true)
}