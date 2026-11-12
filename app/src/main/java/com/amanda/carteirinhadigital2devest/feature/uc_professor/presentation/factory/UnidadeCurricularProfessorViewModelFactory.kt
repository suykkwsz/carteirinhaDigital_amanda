package com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amanda.carteirinhadigital2devest.feature.uc_professor.domain.repository.UnidadeCurricularProfessorRepository
import com.amanda.carteirinhadigital2devest.feature.uc_professor.presentation.UnidadeCurricularProfessorViewModel

class UnidadeCurricularProfessorViewModelFactory(
    private val repository: UnidadeCurricularProfessorRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnidadeCurricularProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnidadeCurricularProfessorViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
