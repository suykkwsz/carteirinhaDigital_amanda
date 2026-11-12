package com.amanda.carteirinhadigital2devest.feature.uc_professor.data.remote.service

import com.amanda.carteirinhadigital2devest.feature.uc_professor.data.remote.dto.UnidadeCurricularProfessorDto
import retrofit2.http.GET

interface UnidadeCurricularProfessorApi {

    @GET("professores/me/unidades-curriculares")
    suspend fun listar(): List<UnidadeCurricularProfessorDto>
}
