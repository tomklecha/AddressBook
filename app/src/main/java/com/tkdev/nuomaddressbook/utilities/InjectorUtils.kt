package com.tkdev.nuomaddressbook.utilities

import android.content.Context
import com.tkdev.nuomaddressbook.data.ContactDatabase
import com.tkdev.nuomaddressbook.model.ErrorMapper
import com.tkdev.nuomaddressbook.model.ErrorMapperDefault
import com.tkdev.nuomaddressbook.model.NewContactValidator
import com.tkdev.nuomaddressbook.model.NewContactValidatorDefault
import com.tkdev.nuomaddressbook.repository.ContactsRepository
import com.tkdev.nuomaddressbook.repository.NewContactsRepository
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModelFactory
import com.tkdev.nuomaddressbook.viewmodels.NewContactsViewModelFactory

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

    private fun getNewContactsRepository(context: Context): NewContactsRepository {
        return NewContactsRepository.getInstance(
            ContactDatabase.getDatabase(context.applicationContext).contactDao()
        )
    }

    fun provideNewContactsViewModelFactory(
        context: Context
    ): NewContactsViewModelFactory {
        val repository = getNewContactsRepository(context)
        val validator = getNewContactValidator()
        val mapper = getErrorMapper(context)
        return NewContactsViewModelFactory(
            repository, validator, mapper
        )
    }

    private fun getErrorMapper(context: Context): ErrorMapper =
        ErrorMapperDefault(getStringWrapper(context))

    private fun getStringWrapper(context: Context): CommonStringResourceWrapper =
        CommonStringResourceWrapper(context)

    private fun getNewContactValidator(): NewContactValidator = NewContactValidatorDefault()
}