package com.tkdev.nuomaddressbook.viewmodels

import android.content.Context
import com.tkdev.nuomaddressbook.data.ContactDatabase
import com.tkdev.nuomaddressbook.repository.ContactsRepository

object InjectorUtils {

    private fun getContactsRepository(context: Context): ContactsRepository {
        return ContactsRepository.getInstance(
            ContactDatabase.getDatabase(context.applicationContext).contactDao()
        )
    }

    fun provideContactsViewModelFactory(
        context: Context
    ): ContactsViewModelFactory {
        val repository = getContactsRepository(context)
        return ContactsViewModelFactory(repository)
    }
}