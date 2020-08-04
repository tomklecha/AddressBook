package com.tkdev.nuomaddressbook.repository

import com.tkdev.nuomaddressbook.data.ContactDao

class ContactsRepository(private val dao: ContactDao) {

    fun getContacts() = dao.getContacts()

    companion object {

        @Volatile
        private var instance: ContactsRepository? = null

        fun getInstance(dao: ContactDao) =
            instance ?: synchronized(this) {
                instance ?: ContactsRepository(dao).also { instance = it }
            }
    }
}