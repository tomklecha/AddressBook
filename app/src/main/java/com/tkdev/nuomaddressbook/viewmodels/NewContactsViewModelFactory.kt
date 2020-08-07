package com.tkdev.nuomaddressbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkdev.nuomaddressbook.model.ErrorMapper
import com.tkdev.nuomaddressbook.model.NewContactValidator
import com.tkdev.nuomaddressbook.repository.NewContactsRepository

class NewContactsViewModelFactory(
    private val repository: NewContactsRepository,
    private val validator: NewContactValidator,
    private val mapper: ErrorMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewContactsViewModel(repository, validator, mapper) as T
    }
}