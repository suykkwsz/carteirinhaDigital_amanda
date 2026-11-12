package com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.model

data class TurmaProfessor(
    val id: String,
    val codigo: String,
    val curso: String,
    val semestre: String,
    val quantidadeAlunos: Int,
    val diasSemana: List<String>,
    val horarioInicio: String,
    val horarioFim: String
)
