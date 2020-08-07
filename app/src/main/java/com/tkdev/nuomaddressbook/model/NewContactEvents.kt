package com.tkdev.nuomaddressbook.model

internal sealed class NewContactEvents {
    data class OnFirstNameChanged(val value: String) : NewContactEvents()
    data class OnLastNameChanged(val value: String) : NewContactEvents()
    data class OnEmailChanged(val value: String) : NewContactEvents()
    data class OnPhoneNumberChanged(val value: String) : NewContactEvents()
    data class OnAddressChanged(val value: String) : NewContactEvents()
}