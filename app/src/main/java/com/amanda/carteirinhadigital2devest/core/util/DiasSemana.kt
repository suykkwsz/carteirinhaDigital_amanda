package com.amanda.carteirinhadigital2devest.core.util

import java.util.Locale

/** Aceita "SEGUNDA", "Segunda-feira", "segunda" etc. e devolve "Seg". */
private fun abreviarDia(dia: String): String {
    val limpo = dia.trim().lowercase(Locale.forLanguageTag("pt-BR")).removeSuffix("-feira")
    return limpo.take(3).replaceFirstChar { it.uppercase() }
}

fun List<String>.formatarDiasSemana(): String =
    if (isEmpty()) "-" else joinToString(", ") { abreviarDia(it) }

fun formatarHorario(inicio: String, fim: String): String = "$inicio às $fim"
