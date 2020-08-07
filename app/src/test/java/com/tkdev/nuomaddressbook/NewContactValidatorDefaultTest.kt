package com.tkdev.nuomaddressbook

import com.tkdev.nuomaddressbook.model.*
import junit.framework.Assert.assertEquals
import org.junit.Test

class NewContactValidatorDefaultTest {

    private val validator = NewContactValidatorDefault()

    // Tests for firstNameValidation
    @Test
    fun `GIVEN valid firstName, WHEN firstNameValidation, THEN return valid`() {
        //GIVEN
        val firstName = "Tomasz"
        val expected = FirstNameValidator.Valid

        //WHEN
        val result = validator.firstNameValidation(firstName)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN empty string, WHEN firstNameValidation, THEN return error`() {
        //GIVEN
        val firstName = ""
        val error = FirstNameValidatorError.EmptyFirstName
        val expected = FirstNameValidator.Error(error)

        //WHEN
        val result = validator.firstNameValidation(firstName)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN too small string, WHEN firstNameValidation, THEN return error`() {
        //GIVEN
        val firstName = "Tom"
        val error = FirstNameValidatorError.TooSmall
        val expected = FirstNameValidator.Error(error)

        //WHEN
        val result = validator.firstNameValidation(firstName)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN wrong characters, WHEN firstNameValidation, THEN return error`() {
        //GIVEN
        val firstName = "T@masz"
        val error = FirstNameValidatorError.WrongCharacters
        val expected = FirstNameValidator.Error(error)

        //WHEN
        val result = validator.firstNameValidation(firstName)

        //THEN
        assertEquals(result, expected)
    }

    // Tests for lastNameValidation
    @Test
    fun `GIVEN valid lastName, WHEN lastNameValidation, THEN return valid`() {
        //GIVEN
        val lastName = "Klecha"
        val expected = LastNameValidator.Valid

        //WHEN
        val result = validator.lastNameValidation(lastName)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN empty string, WHEN lastNameValidation, THEN return error`() {
        //GIVEN
        val lastName = ""
        val error = LastNameValidatorError.EmptyLastName
        val expected = LastNameValidator.Error(error)

        //WHEN
        val result = validator.lastNameValidation(lastName)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN too small string, WHEN lastNameValidation, THEN return error`() {
        //GIVEN
        val lastName = "Kle"
        val error = LastNameValidatorError.TooSmall
        val expected = LastNameValidator.Error(error)

        //WHEN
        val result = validator.lastNameValidation(lastName)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN wrong characters, WHEN lastNameValidation, THEN return error`() {
        //GIVEN
        val lastName = "Kl3cha"
        val error = LastNameValidatorError.WrongCharacters
        val expected = LastNameValidator.Error(error)

        //WHEN
        val result = validator.lastNameValidation(lastName)

        //THEN
        assertEquals(result, expected)
    }

    //Tests for emailValidation
    @Test
    fun `GIVEN valid email, WHEN emailValidation, THEN return valid`() {
        //GIVEN
        val email = "tom.klecha.dev@gmail.com"
        val expected = EmailValidator.Valid

        //WHEN
        val result = validator.emailValidation(email)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN empty string, WHEN emailValidation, THEN return error`() {
        //GIVEN
        val email = ""
        val error = EmailValidatorError.EmptyEmail
        val expected = EmailValidator.Error(error)

        //WHEN
        val result = validator.emailValidation(email)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN too small string, WHEN emailValidation, THEN return error`() {
        //GIVEN
        val email = "tom"
        val error = EmailValidatorError.TooSmall
        val expected = EmailValidator.Error(error)

        //WHEN
        val result = validator.emailValidation(email)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN missing at sign, WHEN emailValidation, THEN return error`() {
        //GIVEN
        val email = "tom.klecha.devATgmail.com"
        val error = EmailValidatorError.MissingAtSign
        val expected = EmailValidator.Error(error)

        //WHEN
        val result = validator.emailValidation(email)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN too long domain, WHEN emailValidation, THEN return error`() {
        //GIVEN
        val email = "tom.klecha.dev@gmail.com.uk.pl"
        val error = EmailValidatorError.TooLongDomain
        val expected = EmailValidator.Error(error)

        //WHEN
        val result = validator.emailValidation(email)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN wrong domain usage, WHEN emailValidation, THEN return error`() {
        //GIVEN
        val email = "tom.klecha.dev@gmail.c"
        val error = EmailValidatorError.WrongEmailDomainUsage
        val expected = EmailValidator.Error(error)

        //WHEN
        val result = validator.emailValidation(email)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN wrong characters, WHEN emailValidation, THEN return error`() {
        //GIVEN
        val email = "tom!klecha@gmail.com"
        val error = EmailValidatorError.WrongCharacters
        val expected = EmailValidator.Error(error)

        //WHEN
        val result = validator.emailValidation(email)

        //THEN
        assertEquals(result, expected)
    }

    //Tests for phoneNumberValidation
    @Test
    fun `GIVEN valid phone number, WHEN phoneNumberValidation, THEN return valid`() {
        //GIVEN
        val phoneNumber = "07123456789"
        val expected = PhoneNumberValidator.Valid

        //WHEN
        val result = validator.phoneNumberValidation(phoneNumber)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN empty string, WHEN phoneNumberValidation, THEN return error`() {
        //GIVEN
        val phoneNumber = ""
        val error = PhoneNumberValidatorError.EmptyNumber
        val expected = PhoneNumberValidator.Error(error)

        //WHEN
        val result = validator.phoneNumberValidation(phoneNumber)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN too small string, WHEN phoneNumberValidation, THEN return error`() {
        //GIVEN
        val phoneNumber = "07123"
        val error = PhoneNumberValidatorError.TooSmall
        val expected = PhoneNumberValidator.Error(error)

        //WHEN
        val result = validator.phoneNumberValidation(phoneNumber)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN wrong prefix, WHEN phoneNumberValidation, THEN return error`() {
        //GIVEN
        val phoneNumber = "99123456789"
        val error = PhoneNumberValidatorError.WrongPrefix
        val expected = PhoneNumberValidator.Error(error)

        //WHEN
        val result = validator.phoneNumberValidation(phoneNumber)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN too long number, WHEN phoneNumberValidation, THEN return error`() {
        //GIVEN
        val phoneNumber = "0712345678910"
        val error = PhoneNumberValidatorError.TooLongNumber
        val expected = PhoneNumberValidator.Error(error)

        //WHEN
        val result = validator.phoneNumberValidation(phoneNumber)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN wrong characters, WHEN phoneNumberValidation, THEN return valid`() {
        //GIVEN
        val phoneNumber = "07!23456789"
        val error = PhoneNumberValidatorError.WrongCharacters
        val expected = PhoneNumberValidator.Error(error)

        //WHEN
        val result = validator.phoneNumberValidation(phoneNumber)

        //THEN
        assertEquals(result, expected)
    }

    //Tests for addressValidation
    @Test
    fun `GIVEN string, WHEN addressValidation, THEN return validation valid`() {
        //GIVEN
        val address = "Any String, with numbers from 0 to 9, will be valid"
        val expected = AddressValidator.Valid

        //WHEN
        val result = validator.addressValidation(address)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN empty string, WHEN addressValidation, THEN return validation valid`() {
        //GIVEN
        val address = ""
        val expected = AddressValidator.Valid

        //WHEN
        val result = validator.addressValidation(address)

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN wrong characters, WHEN addressValidation, THEN return validation error`() {
        //GIVEN
        val address = "@ddress with w4ong Character$"
        val error = AddressValidatorError.WrongCharacters
        val expected = AddressValidator.Error(error)

        //WHEN
        val result = validator.addressValidation(address)

        //THEN
        assertEquals(result, expected)
    }
}