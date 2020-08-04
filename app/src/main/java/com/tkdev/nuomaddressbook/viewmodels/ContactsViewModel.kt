package com.tkdev.nuomaddressbook.viewmodels

import androidx.lifecycle.*
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.repository.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val repository: ContactsRepository
) : ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>()

    val contacts: LiveData<List<Contact>> = _contacts.switchMap {
        liveData { emit(it) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _contacts.postValue(repository.getContacts())
        }
    }
}