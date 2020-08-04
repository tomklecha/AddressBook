package com.tkdev.nuomaddressbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkdev.nuomaddressbook.repository.SingleContactsRepository

class SingleContactsViewModelFactory(
    private val repository: SingleContactsRepository,
    private val contactId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingleContactsViewModel(repository, contactId) as T
    }
}