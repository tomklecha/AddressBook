package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.adapters.ContactsAdapter
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.databinding.FragmentContactsBinding
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_contacts.*

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
                callback = object : Callback {
                    override fun createContact() {
                        findNavController().navigate(R.id.newContactFragment)
                    }
                }
            }

        contactsAdapter = ContactsAdapter(this)

        contactsViewModel.contacts.observe(viewLifecycleOwner) { list ->
            updateUI(list)
        }

        binding.contactsRecyclerView.apply {
            adapter = contactsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
        binding.contactsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        return binding.root
    }

    private fun updateUI(list: List<Contact>) {
        when (list.isEmpty()) {
            true -> imageView.visibility = View.VISIBLE
            false -> {
                imageView.visibility = View.INVISIBLE
                contactsAdapter.submitList(list)
            }
        }
    }

    interface Callback {
        fun createContact()
    }

    override fun onItemClickListener(contactId: Int) {
        contactsViewModel.getContact(contactId)
        findNavController().navigate(R.id.openCurrentContact)
    }
}


