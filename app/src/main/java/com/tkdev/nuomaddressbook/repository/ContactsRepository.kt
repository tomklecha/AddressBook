package com.tkdev.nuomaddressbook.repository

import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.data.ContactDao

class ContactsRepository(private val dao: ContactDao) {

    fun getContacts() = dao.getContacts()

    fun insertAll(list: List<Contact>) = dao.insertAll(list)

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