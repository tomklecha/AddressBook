package com.tkdev.nuomaddressbook.repository

import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.data.ContactDao

class NewContactsRepository(private val dao: ContactDao) {

    fun saveContact(contact: Contact) = dao.insert(contact)

    companion object {

        @Volatile
        private var instance: NewContactsRepository? = null

        fun getInstance(dao: ContactDao) =
            instance ?: synchronized(this) {
                instance ?: NewContactsRepository(dao).also { instance = it }
            }
    }
}