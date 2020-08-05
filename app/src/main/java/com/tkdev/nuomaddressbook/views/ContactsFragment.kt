package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.adapters.ContactsAdapter
import com.tkdev.nuomaddressbook.databinding.FragmentContactsBinding
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel

class ContactsFragment : Fragment(), ContactsAdapter.ItemListener {

    private lateinit var contactsAdapter: ContactsAdapter

    private val contactsViewModel: ContactsViewModel by activityViewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactsBinding
            .inflate(inflater, container, false).apply {
                viewModel = contactsViewModel
                callback = object : Callback{
                    override fun createContact() {
                        findNavController().navigate(R.id.newContactFragment)
                    }
                }
            }

        contactsAdapter = ContactsAdapter(this)

        contactsViewModel.contacts.observe(viewLifecycleOwner) { list ->
            contactsAdapter.submitList(list)
        }

        binding.contactsRecyclerView.apply {
            adapter = contactsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        return binding.root
    }

    interface Callback {
        fun createContact()
    }

    override fun onItemClickListener(contactId: Int) {
        contactsViewModel.getContact(contactId)
        findNavController().navigate(R.id.openCurrentContact)
    }
}


