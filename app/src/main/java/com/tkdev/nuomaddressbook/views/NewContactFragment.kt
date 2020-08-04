package com.tkdev.nuomaddressbook.views

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_new_contact.*

class NewContactFragment : Fragment(R.layout.fragment_new_contact) {

    private val contactsViewModel: ContactsViewModel by activityViewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onStart() {
        super.onStart()

        saveButton.setOnClickListener {
            contactsViewModel.saveContact(
                Contact(
                    newContactFirstName.text.toString(),
                    newContactLastName.text.toString(),
                    newContactEmail.text.toString(),
                    newContactPhoneNumber.text.toString(),
                    newContactAddress.text.toString()
                )
            )
            findNavController().navigateUp()

        }
    }
}