package com.tkdev.nuomaddressbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkdev.nuomaddressbook.repository.ContactsRepository

class ContactsViewModelFactory(
    private val repository: ContactsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(repository) as T
    }
}