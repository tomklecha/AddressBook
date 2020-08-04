package com.tkdev.nuomaddressbook.utilities

import android.content.Context
import com.tkdev.nuomaddressbook.data.ContactDatabase
import com.tkdev.nuomaddressbook.repository.ContactsRepository
import com.tkdev.nuomaddressbook.repository.SingleContactsRepository
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModelFactory
import com.tkdev.nuomaddressbook.viewmodels.SingleContactsViewModelFactory

object InjectorUtils {

    private fun getContactsRepository(context: Context): ContactsRepository {
        return ContactsRepository.getInstance(
            ContactDatabase.getDatabase(context.applicationContext).contactDao()
        )
    }

    fun provideContactsViewModelFactory(
        context: Context
    ): ContactsViewModelFactory {
        val repository =
            getContactsRepository(
                context
            )
        return ContactsViewModelFactory(
            repository
        )
    }

    private fun getSingleContactsRepository(context: Context): SingleContactsRepository {
        return SingleContactsRepository.getInstance(
            ContactDatabase.getDatabase(context.applicationContext).contactDao()
        )
    }

    fun provideSingleContactsViewModelFactory(
        context: Context,
        contactId: Int
    ): SingleContactsViewModelFactory {
        val repository =
            getSingleContactsRepository(
                context
            )
        return SingleContactsViewModelFactory(
            repository,
            contactId
        )
    }

}