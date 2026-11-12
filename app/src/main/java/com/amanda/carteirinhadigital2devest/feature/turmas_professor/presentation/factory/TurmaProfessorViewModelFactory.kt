package com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.domain.repository.TurmaProfessorRepository
import com.amanda.carteirinhadigital2devest.feature.turmas_professor.presentation.TurmaProfessorViewModel

class TurmaProfessorViewModelFactory(
    private val repository: TurmaProfessorRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TurmaProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TurmaProfessorViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
