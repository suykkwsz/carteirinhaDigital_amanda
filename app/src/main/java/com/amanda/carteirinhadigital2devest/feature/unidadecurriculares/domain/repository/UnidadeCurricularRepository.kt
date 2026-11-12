package com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository

import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listar():Result<List<UnidadeCurricular>>
}