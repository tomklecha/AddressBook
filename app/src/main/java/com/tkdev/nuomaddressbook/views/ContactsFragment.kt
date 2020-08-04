package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.adapters.ContactsAdapter
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel
import com.tkdev.nuomaddressbook.viewmodels.InjectorUtils
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.coroutines.flow.callbackFlow

class ContactsFragment : Fragment(R.layout.fragment_contacts), ContactsAdapter.Listener {

    private lateinit var contactsAdapter: ContactsAdapter

    private val contactsViewModel: ContactsViewModel by viewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onStart() {
        super.onStart()

        contactsAdapter = ContactsAdapter(requireContext(), this)

        contactsRecyclerView.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        contactsViewModel.contacts.observe(this, Observer { list ->
            contactsAdapter.setContactList(list)
        })

        contactsViewModel.contact.observe(this, Observer {
            Toast.makeText(requireContext(), it.firstName, Toast.LENGTH_SHORT).show()
        })

    }

    override fun onItemSelected(contactId: Int) {
        contactsViewModel.getContactById(contactId)
    }

}


