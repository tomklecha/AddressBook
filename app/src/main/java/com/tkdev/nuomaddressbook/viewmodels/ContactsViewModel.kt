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

    private val _searchContacts = MutableLiveData<List<Contact>>()

    val contact: LiveData<Contact> = _contact

    val searchContacts: LiveData<List<Contact>> = _searchContacts

    val contacts: LiveData<List<Contact>> = repository.getContacts().asLiveData()

    fun getSearchContacts(search: String?)=
        viewModelScope.launch(Dispatchers.IO) {
            _searchContacts.postValue(repository.getSearchContacts(search))
        }

    fun getContact(contact: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            _contact.postValue(repository.getContact(contact))
        }

}