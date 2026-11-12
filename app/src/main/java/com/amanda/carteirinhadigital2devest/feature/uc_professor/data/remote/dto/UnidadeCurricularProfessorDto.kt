package com.amanda.carteirinhadigital2devest.feature.uc_professor.data.remote.dto

import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.model.UnidadeCurricularProfessor
import kotlinx.serialization.Serializable

@Serializable
data class UnidadeCurricularProfessorDto(
    val id: String,
    val nome: String,
    val turmaId: String,
    val turma: String,
    val cargaHoraria: Int,
    val quantidadeAulas: Int,
    val aulasRealizadas: Int,
    val diasSemana: List<String> = emptyList(),
    val horarioInicio: String,
    val horarioFim: String
) {
    fun toDomain() = UnidadeCurricularProfessor(
        id = id,
        nome = nome,
        turmaId = turmaId,
        turma = turma,
        cargaHoraria = cargaHoraria,
        quantidadeAulas = quantidadeAulas,
        aulasRealizadas = aulasRealizadas,
        diasSemana = diasSemana,
        horarioInicio = horarioInicio,
        horarioFim = horarioFim
    )
}
