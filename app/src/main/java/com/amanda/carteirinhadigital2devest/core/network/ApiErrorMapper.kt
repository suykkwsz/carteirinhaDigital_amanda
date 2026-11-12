package com.amanda.carteirinhadigital2devest.core.network

import retrofit2.HttpException
import java.io.IOException

/** Converte falhas de rede/HTTP em mensagens amigáveis para a UI. */
fun Throwable.toApiError(recurso: String): Throwable = when (this) {
    is HttpException -> when (code()) {
        401 -> IllegalStateException("Sua sessão expirou. Faça login novamente.")
        403 -> IllegalStateException("Acesso negado ao carregar $recurso.")
        else -> IllegalStateException("Erro ao carregar $recurso (${code()}).")
    }
    is IOException -> IllegalStateException("Não foi possível conectar à API.")
    else -> IllegalStateException(message ?: "Erro ao carregar $recurso.")
}
