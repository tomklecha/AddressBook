package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_new_contact, container, false)
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