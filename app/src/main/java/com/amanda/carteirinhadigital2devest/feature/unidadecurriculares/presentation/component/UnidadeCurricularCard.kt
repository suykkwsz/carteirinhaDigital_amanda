package com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.presentation.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amanda.carteirinhadigital2devest.core.designsystem.theme.AppPalette
import com.amanda.carteirinhadigital2devest.feature.unidadecurriculares.domain.model.UnidadeCurricular

@Composable
fun UnidadeCurricularCard(
    modifier: Modifier = Modifier,
    unidadeCurricular: UnidadeCurricular
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCFAFE)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            Text(
                text = unidadeCurricular.nome,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = AppPalette.purpleButton
            )
            Text(
                text = "Professor: ${unidadeCurricular.professor}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF514956)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "N1: ${unidadeCurricular.nota1}")
                Text(text = "N2: ${unidadeCurricular.nota2}")
                Text(
                    text = "Média: ${unidadeCurricular.media}",
                    color = AppPalette.purpleButton,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = "Faltas: ${unidadeCurricular.faltas}",
                style = MaterialTheme.typography.bodyMedium,
                color = AppPalette.purpleButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnidadeCurricularCardPreview() {
    UnidadeCurricularCard(
        unidadeCurricular = UnidadeCurricular(
            id = "1",
            nome = "Matemática",
            professor = "Dr. Silva",
            nota1 = 8.5,
            nota2 = 7.0,
            media = 7.75,
            faltas = 2
        )
    )
}
