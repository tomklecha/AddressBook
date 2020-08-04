package com.tkdev.nuomaddressbook.viewmodels

import androidx.lifecycle.*
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.repository.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val repository: ContactsRepository
) : ViewModel() {

    private val _contact = MutableLiveData<Contact>()

    private val _contacts = MutableLiveData<List<Contact>>()

    val contact: LiveData<Contact> = _contact.switchMap { liveData { emit(it) } }

    val contacts: LiveData<List<Contact>> = _contacts.switchMap { liveData { emit(it) } }

    fun getContact(contact: Int) =
        viewModelScope.launch(Dispatchers.IO) {
        _contact.postValue(repository.getContact(contact))
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _contacts.postValue(repository.getContacts())
        }
    }
}