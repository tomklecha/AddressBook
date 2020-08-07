package com.tkdev.nuomaddressbook.repository

import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.data.ContactDao
import kotlinx.coroutines.flow.Flow

class ContactsRepository(private val dao: ContactDao) {

    fun getSearchContacts(search: String?): List<Contact> = dao.getSearchContacts(search)

    fun getContacts(): Flow<List<Contact>> = dao.getContacts()

    fun getContact(contactId: Int) = dao.getContact(contactId)

    companion object {

        @Volatile
        private var instance: ContactsRepository? = null

        fun getInstance(dao: ContactDao) =
            instance ?: synchronized(this) {
                instance ?: ContactsRepository(dao).also { instance = it }
            }
    }
}