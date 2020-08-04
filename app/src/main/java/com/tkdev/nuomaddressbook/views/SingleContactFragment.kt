package com.tkdev.nuomaddressbook.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_single_contact.*

class SingleContactFragment : Fragment(R.layout.fragment_single_contact) {

    private val contactsViewModel: ContactsViewModel by activityViewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onStart() {
        super.onStart()

        contactsViewModel.contact.observe(viewLifecycleOwner, Observer {
            singleContactName.text = it.firstName
        })

    }
}
