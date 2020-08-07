package com.tkdev.nuomaddressbook.model

sealed class FirstNameValidator {
    object Valid : FirstNameValidator()
    data class Error(val error: FirstNameValidatorError) : FirstNameValidator()
}

sealed class LastNameValidator {
    object Valid : LastNameValidator()
    data class Error(val error: LastNameValidatorError) : LastNameValidator()
}

sealed class EmailValidator {
    object Valid : EmailValidator()
    data class Error(val error: EmailValidatorError) : EmailValidator()
}

sealed class PhoneNumberValidator {
    object Valid : PhoneNumberValidator()
    data class Error(val error: PhoneNumberValidatorError) : PhoneNumberValidator()
}

sealed class AddressValidator {
    object Valid : AddressValidator()
    data class Error(val error: AddressValidatorError) : AddressValidator()
}

sealed class FirstNameValidatorError {
    object EmptyFirstName : FirstNameValidatorError()
    object TooSmall : FirstNameValidatorError()
    object WrongCharacters : FirstNameValidatorError()
}

sealed class LastNameValidatorError {
    object EmptyLastName : LastNameValidatorError()
    object TooSmall : LastNameValidatorError()
    object WrongCharacters : LastNameValidatorError()
}

sealed class EmailValidatorError {
    object EmptyEmail : EmailValidatorError()
    object TooSmall : EmailValidatorError()
    object MissingAtSign : EmailValidatorError()
    object TooLongDomain : EmailValidatorError()
    object WrongEmailDomainUsage : EmailValidatorError()
    object WrongCharacters : EmailValidatorError()
}

sealed class PhoneNumberValidatorError {
    object EmptyNumber : PhoneNumberValidatorError()
    object TooSmall : PhoneNumberValidatorError()
    object WrongPrefix : PhoneNumberValidatorError()
    object TooLongNumber : PhoneNumberValidatorError()
    object WrongCharacters : PhoneNumberValidatorError()
}

sealed class AddressValidatorError {
    object WrongCharacters : AddressValidatorError()
}