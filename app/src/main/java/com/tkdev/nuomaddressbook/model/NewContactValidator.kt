package com.tkdev.nuomaddressbook.model

interface NewContactValidator {
    fun firstNameValidation(firstName: String): FirstNameValidator
    fun lastNameValidation(lastName: String): LastNameValidator
    fun emailValidation(email: String): EmailValidator
    fun phoneNumberValidation(phoneNumber: String): PhoneNumberValidator
    fun addressValidation(address: String): AddressValidator
}

class NewContactValidatorDefault() : NewContactValidator {

    override fun firstNameValidation(firstName: String): FirstNameValidator =
        when {
            firstName == "" -> FirstNameValidator.Error(FirstNameValidatorError.EmptyFirstName)
            firstName.length < 4 -> FirstNameValidator.Error(
                FirstNameValidatorError.TooSmall
            )
            Regex("[a-zA-Z]{4,}+").matches(firstName) -> FirstNameValidator.Valid
            else -> FirstNameValidator.Error(FirstNameValidatorError.WrongCharacters)
        }

    override fun lastNameValidation(lastName: String): LastNameValidator =
        when {
            lastName == "" -> LastNameValidator.Error(LastNameValidatorError.EmptyLastName)
            lastName.length < 4 -> LastNameValidator.Error(
                LastNameValidatorError.TooSmall
            )
            Regex("[a-zA-Z]{4,}+").matches(lastName) -> LastNameValidator.Valid
            else -> LastNameValidator.Error(LastNameValidatorError.WrongCharacters)
        }

    override fun emailValidation(email: String): EmailValidator =
        when {
            email == "" -> EmailValidator.Error(EmailValidatorError.EmptyEmail)
            email.length < 4 -> EmailValidator.Error(EmailValidatorError.TooSmall)
            !Regex("[@]").containsMatchIn(email) -> EmailValidator.Error(EmailValidatorError.MissingAtSign)
            !Regex("[.][a-z]{2,}+$").containsMatchIn(email.toLowerCase())
            -> EmailValidator.Error(EmailValidatorError.WrongEmailDomainUsage)
            Regex("[a-z0-9._]+[@][a-z0-9]+[.][a-z]{2,}+[.][a-z]{2,}+[.][a-zA-Z0-9.]+").matches(email.toLowerCase())
            -> EmailValidator.Error(EmailValidatorError.TooLongDomain)
            (Regex("[a-z0-9._]+[@][a-z0-9]+[.][a-z]{2,}+$").matches(email.toLowerCase()))
            -> EmailValidator.Valid
            (Regex("[a-z0-9._]+[@][a-z0-9]+[.][a-z]{2,}+[.][a-z]{2,}+$").matches(email.toLowerCase()))
            -> EmailValidator.Valid
            else -> EmailValidator.Error(EmailValidatorError.WrongCharacters)
        }

    override fun phoneNumberValidation(phoneNumber: String): PhoneNumberValidator =
        when {
            phoneNumber == "" -> PhoneNumberValidator.Error(PhoneNumberValidatorError.EmptyNumber)
            !Regex("^[0][7]").containsMatchIn(phoneNumber) -> PhoneNumberValidator.Error(
                PhoneNumberValidatorError.WrongPrefix
            )
            phoneNumber.length > 11 -> PhoneNumberValidator.Error(PhoneNumberValidatorError.TooLongNumber)
            phoneNumber.length < 11 -> PhoneNumberValidator.Error(PhoneNumberValidatorError.TooSmall)
            Regex("^[0][7][0-9]{9}$").containsMatchIn(phoneNumber.toLowerCase())
            -> PhoneNumberValidator.Valid
            else -> PhoneNumberValidator.Error(PhoneNumberValidatorError.WrongCharacters)
        }

    override fun addressValidation(address: String): AddressValidator =
        when {
            address == "" -> AddressValidator.Valid
            Regex("^[a-zA-Z0-9,]+").containsMatchIn(address) -> AddressValidator.Valid
            else -> AddressValidator.Error(AddressValidatorError.WrongCharacters)
        }

}