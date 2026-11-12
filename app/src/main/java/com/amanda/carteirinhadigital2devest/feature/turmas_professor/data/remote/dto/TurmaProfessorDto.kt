package com.amanda.carteirinhadigital2devest.feature.turmas_professor.data.remote.dto

import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.model.TurmaProfessor
import kotlinx.serialization.Serializable

@Serializable
data class TurmaProfessorDto(
    val id: String,
    val codigo: String,
    val curso: String,
    val semestre: String,
    val quantidadeAlunos: Int,
    val diasSemana: List<String> = emptyList(),
    val horarioInicio: String,
    val horarioFim: String
) {
    fun toDomain() = TurmaProfessor(
        id = id,
        codigo = codigo,
        curso = curso,
        semestre = semestre,
        quantidadeAlunos = quantidadeAlunos,
        diasSemana = diasSemana,
        horarioInicio = horarioInicio,
        horarioFim = horarioFim
    )
}
