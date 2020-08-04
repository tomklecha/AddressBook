package com.tkdev.nuomaddressbook.repository

import com.tkdev.nuomaddressbook.data.ContactDao

class SingleContactsRepository(private val dao: ContactDao) {

    fun getContact(contactId: Int) = dao.getContact(contactId)

    companion object {

        @Volatile
        private var instance: SingleContactsRepository? = null

        fun getInstance(dao: ContactDao) =
            instance ?: synchronized(this) {
                instance ?: SingleContactsRepository(dao).also { instance = it }
            }
    }
}