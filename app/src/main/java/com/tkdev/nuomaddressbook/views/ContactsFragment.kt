package com.tkdev.nuomaddressbook.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel
import com.tkdev.nuomaddressbook.viewmodels.InjectorUtils

class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    private val contactsViewModel: ContactsViewModel by viewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

}

