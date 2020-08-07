package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.databinding.FragmentSingleContactBinding
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.viewmodels.ContactsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class SingleContactFragment() : Fragment() {

    private val contactsViewModel: ContactsViewModel by activityViewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            FragmentSingleContactBinding.inflate(inflater, container, false).apply {
                viewModel = contactsViewModel
                lifecycleOwner = viewLifecycleOwner
            }

        contactsViewModel.contact.observe(viewLifecycleOwner) { list ->
            activity?.appbar?.toolbar?.title =
                resources.getString(R.string.contact_name, list.firstName, list.lastName)
        }

        return binding.root
    }
}
