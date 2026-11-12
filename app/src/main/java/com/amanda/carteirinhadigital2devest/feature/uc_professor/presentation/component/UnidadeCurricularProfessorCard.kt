package com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.amanda.carteirinhadigital2devest.core.util.formatarDiasSemana
import com.amanda.carteirinhadigital2devest.core.util.formatarHorario
import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.model.UnidadeCurricularProfessor

@Composable
fun UnidadeCurricularProfessorCard(
    unidade: UnidadeCurricularProfessor,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFAFA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = unidade.nome, style = MaterialTheme.typography.titleLarge, color = AppPalette.redButton)
            Text(
                text = "Turma: ${unidade.turma}",
                style = MaterialTheme.typography.bodyMedium,
                color = AppPalette.redButton
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Carga horária: ${unidade.cargaHoraria}h")
                Text(text = "${unidade.quantidadeAulas} aulas")
            }
            Text(
                text = "Aulas realizadas: ${unidade.aulasRealizadas}/${unidade.quantidadeAulas}",
                style = MaterialTheme.typography.bodyMedium
            )
            LinearProgressIndicator(
                progress = { unidade.progresso },
                modifier = Modifier.fillMaxWidth(),
                color = AppPalette.redButton,
                trackColor = AppPalette.redButton.copy(alpha = 0.14f)
            )
            Text(text = "Dias: ${unidade.diasSemana.formatarDiasSemana()}")
            Text(text = "Horário: ${formatarHorario(unidade.horarioInicio, unidade.horarioFim)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnidadeCurricularProfessorCardPreview() {
    CarteirinhaDigital2DEVESTTheme {
        UnidadeCurricularProfessorCard(
            unidade = UnidadeCurricularProfessor(
                id = "10",
                nome = "Programação para Dispositivos Móveis",
                turmaId = "1",
                turma = "2DEVEST-A",
                cargaHoraria = 75,
                quantidadeAulas = 100,
                aulasRealizadas = 40,
                diasSemana = listOf("SEGUNDA", "QUARTA"),
                horarioInicio = "19:00",
                horarioFim = "22:30"
            )
        )
    }
}
