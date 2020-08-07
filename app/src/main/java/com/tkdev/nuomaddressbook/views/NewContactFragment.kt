package com.tkdev.nuomaddressbook.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.databinding.FragmentNewContactBinding
import com.tkdev.nuomaddressbook.utilities.InjectorUtils
import com.tkdev.nuomaddressbook.viewmodels.NewContactsViewModel
import kotlinx.android.synthetic.main.fragment_new_contact.*

class NewContactFragment : Fragment() {

    private val newContactViewModel: NewContactsViewModel by activityViewModels {
        InjectorUtils.provideNewContactsViewModelFactory(requireContext())
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
            .apply {
                viewModel = newContactViewModel
                lifecycleOwner = viewLifecycleOwner
                callback = object : Callback {
                    override fun saveContact() {
                        val contact = Contact(
                            newContactFirstName.text.toString(),
                            newContactLastName.text.toString(),
                            newContactEmail.text.toString(),
                            newContactPhoneNumber.text.toString(),
                            newContactAddress.text.toString()
                        )
                        newContactViewModel.saveContact(contact)
                        findNavController().navigateUp()
                    }
                }
            }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newContactFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newContactViewModel.onFirstNameChanged(newContactFirstName.text.toString())
            }
        })
        newContactLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newContactViewModel.onLastNameChanged(newContactLastName.text.toString())
            }
        })
        newContactEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newContactViewModel.onEmailChanged(newContactEmail.text.toString())
            }
        })
        newContactPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newContactViewModel.onPhoneNumberChanged(newContactPhoneNumber.text.toString())
            }
        })
        newContactAddress.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newContactViewModel.onAddressChanged(newContactAddress.text.toString())
            }
        })
    }

    interface Callback {
        fun saveContact()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }
}

