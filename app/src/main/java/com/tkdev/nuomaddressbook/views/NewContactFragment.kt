package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.databinding.FragmentNewContactBinding
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel

class NewContactFragment : Fragment(R.layout.fragment_new_contact) {

    private val contactsViewModel: ContactsViewModel by activityViewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewContactBinding.inflate(inflater, container, false)

        binding.saveButton.setOnClickListener {
            contactsViewModel.saveContact(
                Contact(
                    binding.newContactFirstName.text.toString(),
                    binding.newContactLastName.text.toString(),
                    binding.newContactEmail.text.toString(),
                    binding.newContactPhoneNumber.text.toString(),
                    binding.newContactAddress.text.toString()
                )
            )
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }
}
