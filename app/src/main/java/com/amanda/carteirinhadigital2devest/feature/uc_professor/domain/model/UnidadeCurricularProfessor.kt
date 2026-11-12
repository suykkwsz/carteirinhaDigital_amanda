package com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.model

data class UnidadeCurricularProfessor(
    val id: String,
    val nome: String,
    val turmaId: String,
    val turma: String,
    val cargaHoraria: Int,
    val quantidadeAulas: Int,
    val aulasRealizadas: Int,
    val diasSemana: List<String>,
    val horarioInicio: String,
    val horarioFim: String
) {
    val progresso: Float
        get() = if (quantidadeAulas > 0) {
            (aulasRealizadas.toFloat() / quantidadeAulas).coerceIn(0f, 1f)
        } else 0f
}
