package com.tkdev.nuomaddressbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.repository.SingleContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleContactsViewModel(
    private val repository: SingleContactsRepository,
    private val contactId: Int
) : ViewModel() {

    private val _contact = MutableLiveData<Contact>()

    val contact: LiveData<Contact> = _contact

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _contact.postValue(repository.getContact(contactId))
        }
    }
}