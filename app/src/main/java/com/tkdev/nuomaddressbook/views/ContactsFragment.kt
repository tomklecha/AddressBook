package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.adapters.ContactsAdapter
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.utilities.TextUtilities
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(R.layout.fragment_contacts), ContactsAdapter.Listener {

    private lateinit var contactsAdapter: ContactsAdapter

    private val contactsViewModel: ContactsViewModel by activityViewModels() {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onStart() {
        super.onStart()

        contactsAdapter = ContactsAdapter(requireContext(), this)

        contactsRecyclerView.apply {
            adapter = contactsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        contactsViewModel.contacts.observe(this, Observer { list ->
            contactsAdapter.setContactList(list)
        })

    }

    override fun onItemSelected(contactId: Int) {
        contactsViewModel.getContact(contactId)
        findNavController().navigate(R.id.openCurrentContact)
    }

}


