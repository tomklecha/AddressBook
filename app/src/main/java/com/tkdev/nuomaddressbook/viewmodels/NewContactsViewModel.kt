package com.tkdev.nuomaddressbook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.model.*
import com.tkdev.nuomaddressbook.repository.NewContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class NewContactsViewModel(
    private val repository: NewContactsRepository,
    private val validator: NewContactValidator,
    private val mapper: ErrorMapper
) : ViewModel() {

    private val _validContact = MutableLiveData<Boolean>()
    private val _firstNameError = MutableLiveData<String>()
    private val _lastNameError = MutableLiveData<String>()
    private val _emailError = MutableLiveData<String>()
    private val _phoneNumberError = MutableLiveData<String>()
    private val _addressError = MutableLiveData<String>()

    val validContact: LiveData<Boolean> = _validContact
    val firstNameError: LiveData<String> = _firstNameError
    val lastNameError: LiveData<String> = _lastNameError
    val emailError: LiveData<String> = _emailError
    val phoneNumberError: LiveData<String> = _phoneNumberError
    val addressError: LiveData<String> = _addressError

    private var firstNameValid = false
    private var lastNameValid = false
    private var emailValid = false
    private var phoneNumberValid = false
    private var addressValid = true
    private var resetButton = true

    private val channel = Channel<NewContactEvents>(Channel.CONFLATED)

    fun saveContact(contact: Contact) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveContact(contact)
            resetButton = false
            updateSaveButton()
        }

    fun onFirstNameChanged(text: String) {
        channel.offer(NewContactEvents.OnFirstNameChanged(text))
    }

    fun onLastNameChanged(text: String) {
        channel.offer(NewContactEvents.OnLastNameChanged(text))
    }

    fun onEmailChanged(text: String) {
        channel.offer(NewContactEvents.OnEmailChanged(text))
    }

    fun onPhoneNumberChanged(text: String) {
        channel.offer(NewContactEvents.OnPhoneNumberChanged(text))
    }

    fun onAddressChanged(text: String) {
        channel.offer(NewContactEvents.OnAddressChanged(text))
    }

    private fun validateValue(receiveChannel: ReceiveChannel<NewContactEvents>) =
        viewModelScope.launch(Dispatchers.IO) {
            for (event in receiveChannel)
                when (event) {
                    is NewContactEvents.OnFirstNameChanged -> {
                        when (val validationEvent = validator.firstNameValidation(event.value)) {
                            is FirstNameValidator.Valid -> {
                                updateUIEvent(event, "")
                                firstNameValid = true
                                updateSaveButton()
                            }
                            is FirstNameValidator.Error -> {
                                updateUIEvent(
                                    event,
                                    mapper.mapFirstNameError(validationEvent.error)
                                )
                                firstNameValid = false
                                updateSaveButton()
                            }
                        }
                    }
                    is NewContactEvents.OnLastNameChanged -> {
                        when (val validationEvent = validator.lastNameValidation(event.value)) {
                            is LastNameValidator.Valid -> {
                                updateUIEvent(event, "")
                                lastNameValid = true
                                updateSaveButton()
                            }
                            is LastNameValidator.Error -> {
                                updateUIEvent(
                                    event,
                                    mapper.mapLastNameError(validationEvent.error)
                                )
                                lastNameValid = false
                                updateSaveButton()
                            }
                        }
                    }
                    is NewContactEvents.OnEmailChanged -> {
                        when (val validationEvent = validator.emailValidation(event.value)) {
                            is EmailValidator.Valid -> {
                                updateUIEvent(event, "")
                                emailValid = true
                                updateSaveButton()
                            }
                            is EmailValidator.Error -> {
                                updateUIEvent(
                                    event,
                                    mapper.mapEmailError(validationEvent.error)
                                )
                                emailValid = false
                                updateSaveButton()
                            }
                        }
                    }
                    is NewContactEvents.OnPhoneNumberChanged -> {
                        when (val validationEvent = validator.phoneNumberValidation(event.value)) {
                            is PhoneNumberValidator.Valid -> {
                                updateUIEvent(event, "")
                                phoneNumberValid = true
                                updateSaveButton()
                            }
                            is PhoneNumberValidator.Error -> {
                                updateUIEvent(
                                    event,
                                    mapper.mapPhoneNumberError(validationEvent.error)
                                )
                                phoneNumberValid = false
                                updateSaveButton()
                            }
                        }
                    }
                    is NewContactEvents.OnAddressChanged -> {
                        when (val validationEvent = validator.addressValidation(event.value)) {
                            is AddressValidator.Valid -> {
                                updateUIEvent(event, "")
                                addressValid = true
                                updateSaveButton()
                            }
                            is AddressValidator.Error -> {
                                updateUIEvent(
                                    event,
                                    mapper.mapAddressError(validationEvent.error)
                                )
                                addressValid = false
                                updateSaveButton()
                            }
                        }
                    }
                }
        }


    private fun updateUIEvent(event: NewContactEvents, message: String) =
        viewModelScope.launch(Dispatchers.Main) {
            when (event) {
                is NewContactEvents.OnFirstNameChanged -> _firstNameError.value = message
                is NewContactEvents.OnLastNameChanged -> _lastNameError.value = message
                is NewContactEvents.OnEmailChanged -> _emailError.value = message
                is NewContactEvents.OnPhoneNumberChanged -> _phoneNumberError.value = message
                is NewContactEvents.OnAddressChanged -> _addressError.value = message
            }
        }

    private fun updateSaveButton() =
        viewModelScope.launch(Dispatchers.Main) {
            val checkCondition =
                firstNameValid && lastNameValid && emailValid && phoneNumberValid && addressValid && resetButton
            _validContact.value = checkCondition
            resetButton = true
        }

    init {
        validateValue(channel)
    }
}