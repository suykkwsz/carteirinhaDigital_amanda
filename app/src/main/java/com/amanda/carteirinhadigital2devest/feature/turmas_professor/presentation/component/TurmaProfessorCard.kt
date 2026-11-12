package com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.model.TurmaProfessor

@Composable
fun TurmaProfessorCard(
    turma: TurmaProfessor,
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
            Text(text = turma.codigo, style = MaterialTheme.typography.titleLarge, color = AppPalette.redButton)
            Text(text = turma.curso, style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Semestre: ${turma.semestre}")
                Text(
                    text = "${turma.quantidadeAlunos} alunos",
                    color = AppPalette.redButton
                )
            }
            Text(text = "Dias: ${turma.diasSemana.formatarDiasSemana()}")
            Text(text = "Horário: ${formatarHorario(turma.horarioInicio, turma.horarioFim)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TurmaProfessorCardPreview() {
    CarteirinhaDigital2DEVESTTheme {
        TurmaProfessorCard(
            turma = TurmaProfessor(
                id = "1",
                codigo = "2DEVEST-A",
                curso = "Desenvolvimento de Sistemas",
                semestre = "2026.2",
                quantidadeAlunos = 28,
                diasSemana = listOf("SEGUNDA", "QUARTA"),
                horarioInicio = "19:00",
                horarioFim = "22:30"
            )
        )
    }
}
