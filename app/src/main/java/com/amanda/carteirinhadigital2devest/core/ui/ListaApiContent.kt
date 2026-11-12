package com.amanda.carteirinhadigital2devest.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Trata os 4 estados de uma lista vinda da API:
 * carregando, erro (com "Tentar novamente"), lista vazia e sucesso.
 */
@Composable
fun <T> ListaApiContent(
    isLoading: Boolean,
    errorMessage: String?,
    itens: List<T>,
    mensagemVazia: String,
    onRetry: () -> Unit,
    chave: (T) -> Any,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
    when {
        isLoading -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        errorMessage != null -> Column(
            modifier = modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
            Button(modifier = Modifier.padding(top = 16.dp), onClick = onRetry) {
                Text("Tentar novamente")
            }
        }

        itens.isEmpty() -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(mensagemVazia, textAlign = TextAlign.Center)
        }

        else -> LazyColumn(
            modifier = modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = itens, key = { chave(it) }) { itemContent(it) }
        }
    }
}
