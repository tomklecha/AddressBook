package com.tkdev.nuomaddressbook.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.utilities.TextUtilities
import com.tkdev.nuomaddressbook.viewmodels.SingleContactsViewModel
import kotlinx.android.synthetic.main.fragment_single_contact.*

class SingleContactFragment : Fragment(R.layout.fragment_single_contact) {

    private val singleContactsViewModel: SingleContactsViewModel by viewModels {
        InjectorUtils.provideSingleContactsViewModelFactory(
            requireContext(),
            arguments?.getInt(TextUtilities.CONTACT_ID)!!
        )
    }

    override fun onStart() {
        super.onStart()

        singleContactsViewModel.contact.observe(this, Observer {
            singleContactName.text = it.firstName.toString()
        })

    }
}
