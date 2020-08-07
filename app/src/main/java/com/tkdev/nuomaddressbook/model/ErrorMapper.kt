package com.tkdev.nuomaddressbook.model

import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.utilities.CommonStringResourceWrapper

interface ErrorMapper {
    fun mapFirstNameError(error: FirstNameValidatorError): String
    fun mapLastNameError(error: LastNameValidatorError): String
    fun mapEmailError(error: EmailValidatorError): String
    fun mapPhoneNumberError(error: PhoneNumberValidatorError): String
    fun mapAddressError(error: AddressValidatorError): String
}

class ErrorMapperDefault(private val stringResource: CommonStringResourceWrapper) : ErrorMapper {
    override fun mapFirstNameError(error: FirstNameValidatorError): String =
        when (error) {
            is FirstNameValidatorError.EmptyFirstName -> stringResource.getString(R.string.first_name_error_empty)
            is FirstNameValidatorError.TooSmall -> stringResource.getString(R.string.first_name_error_too_small)
            is FirstNameValidatorError.WrongCharacters -> stringResource.getString(R.string.first_name_error_wrong_characters)
        }

    override fun mapLastNameError(error: LastNameValidatorError): String =
        when (error) {
            is LastNameValidatorError.EmptyLastName -> stringResource.getString(R.string.last_name_error_empty)
            is LastNameValidatorError.TooSmall -> stringResource.getString(R.string.last_name_too_small)
            is LastNameValidatorError.WrongCharacters -> stringResource.getString(R.string.last_name_error_wrong_characters)
        }

    override fun mapEmailError(error: EmailValidatorError): String =
        when (error) {
            is EmailValidatorError.EmptyEmail -> stringResource.getString(R.string.email_error_empty)
            is EmailValidatorError.TooSmall -> stringResource.getString(R.string.email_error_too_small)
            is EmailValidatorError.MissingAtSign -> stringResource.getString(R.string.email_error_missing_at_sign)
            is EmailValidatorError.WrongEmailDomainUsage -> stringResource.getString(R.string.email_error_wrong_domain_usage)
            is EmailValidatorError.TooLongDomain -> stringResource.getString(R.string.email_error_too_long_domain)
            is EmailValidatorError.WrongCharacters -> stringResource.getString(R.string.email_error_wrong_characters)
        }

    override fun mapPhoneNumberError(error: PhoneNumberValidatorError): String =
        when (error) {
            is PhoneNumberValidatorError.EmptyNumber -> stringResource.getString(R.string.phone_number_error_empty)
            is PhoneNumberValidatorError.TooSmall -> stringResource.getString(R.string.phone_number_error_too_small)
            is PhoneNumberValidatorError.WrongPrefix -> stringResource.getString(R.string.phone_number_error_wrong_prefix)
            is PhoneNumberValidatorError.TooLongNumber -> stringResource.getString(R.string.phone_number_error_too_long)
            is PhoneNumberValidatorError.WrongCharacters -> stringResource.getString(R.string.phone_number_error_wrong_characters)
        }

    override fun mapAddressError(error: AddressValidatorError): String =
        when (error) {
            is AddressValidatorError.WrongCharacters -> stringResource.getString(R.string.address_error_wrong_characters)
        }


}