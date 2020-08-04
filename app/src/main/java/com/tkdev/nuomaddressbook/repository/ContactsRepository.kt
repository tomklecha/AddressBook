package com.tkdev.nuomaddressbook.repository

import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.data.ContactDao

class ContactsRepository(private val dao: ContactDao) {

    fun getContacts() = dao.getContacts()

    fun getContact(contactId: Int) = dao.getContact(contactId)

    fun saveContact(contact: Contact) = dao.insert(contact)

    companion object {

        @Volatile
        private var instance: ContactsRepository? = null

        fun getInstance(dao: ContactDao) =
            instance ?: synchronized(this) {
                instance ?: ContactsRepository(dao).also { instance = it }
            }
    }
}