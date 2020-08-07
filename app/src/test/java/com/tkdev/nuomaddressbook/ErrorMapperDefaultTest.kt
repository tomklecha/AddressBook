package com.tkdev.nuomaddressbook

import com.tkdev.nuomaddressbook.model.*
import com.tkdev.nuomaddressbook.utilities.CommonStringResourceWrapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ErrorMapperDefaultTest {

    @MockK
    private lateinit var stringResource: CommonStringResourceWrapper

    @InjectMockKs
    private lateinit var mapper: ErrorMapperDefault

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `GIVEN firstNameValidatorError, WHEN mapFirstNameError, THEN return message`() {
        //GIVEN
        val validatorError = listOf(
            FirstNameValidatorError.EmptyFirstName,
            FirstNameValidatorError.TooSmall,
            FirstNameValidatorError.WrongCharacters
        )
        val expected = listOf("this", "is", "sparta")

        every { stringResource.getString(R.string.first_name_error_empty) } returns expected[0]
        every { stringResource.getString(R.string.first_name_error_too_small) } returns expected[1]
        every { stringResource.getString(R.string.first_name_error_wrong_characters) } returns expected[2]

        //WHEN
        val result = validatorError.map { mapper.mapFirstNameError(it) }

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN lastNameValidatorError, WHEN mapLastNameError, THEN return message`() {
        //GIVEN
        val validatorError = listOf(
            LastNameValidatorError.EmptyLastName,
            LastNameValidatorError.TooSmall,
            LastNameValidatorError.WrongCharacters
        )
        val expected = listOf("this", "is", "sparta")

        every { stringResource.getString(R.string.last_name_error_empty) } returns expected[0]
        every { stringResource.getString(R.string.last_name_too_small) } returns expected[1]
        every { stringResource.getString(R.string.last_name_error_wrong_characters) } returns expected[2]

        //WHEN
        val result = validatorError.map { mapper.mapLastNameError(it) }

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN emailValidatorError, WHEN mapEmailError, THEN return message`() {
        //GIVEN
        val validatorError = listOf(
            EmailValidatorError.EmptyEmail,
            EmailValidatorError.TooSmall,
            EmailValidatorError.MissingAtSign,
            EmailValidatorError.TooLongDomain,
            EmailValidatorError.WrongEmailDomainUsage,
            EmailValidatorError.WrongCharacters
        )
        val expected = listOf("this", "is", "sparta", "thats", "all", "folks")

        every { stringResource.getString(R.string.email_error_empty) } returns expected[0]
        every { stringResource.getString(R.string.email_error_too_small) } returns expected[1]
        every { stringResource.getString(R.string.email_error_missing_at_sign) } returns expected[2]
        every { stringResource.getString(R.string.email_error_too_long_domain) } returns expected[3]
        every { stringResource.getString(R.string.email_error_wrong_domain_usage) } returns expected[4]
        every { stringResource.getString(R.string.email_error_wrong_characters) } returns expected[5]

        //WHEN
        val result = validatorError.map { mapper.mapEmailError(it) }

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN phoneNumberValidatorError, WHEN mapPhoneNumberError, THEN return message`() {
        //GIVEN
        val validatorError = listOf(
            PhoneNumberValidatorError.EmptyNumber,
            PhoneNumberValidatorError.TooSmall,
            PhoneNumberValidatorError.WrongPrefix,
            PhoneNumberValidatorError.TooLongNumber,
            PhoneNumberValidatorError.WrongCharacters
        )
        val expected = listOf("this", "is", "sparta", "harry", "potter")

        every { stringResource.getString(R.string.phone_number_error_empty) } returns expected[0]
        every { stringResource.getString(R.string.phone_number_error_too_small) } returns expected[1]
        every { stringResource.getString(R.string.phone_number_error_wrong_prefix) } returns expected[2]
        every { stringResource.getString(R.string.phone_number_error_too_long) } returns expected[3]
        every { stringResource.getString(R.string.phone_number_error_wrong_characters) } returns expected[4]

        //WHEN
        val result = validatorError.map { mapper.mapPhoneNumberError(it) }

        //THEN
        assertEquals(result, expected)
    }

    @Test
    fun `GIVEN addressValidatorError, WHEN mapAddressError, THEN return message`() {
        //GIVEN
        val validatorError = listOf(
            AddressValidatorError.WrongCharacters
        )
        val expected = listOf("sparta")

        every { stringResource.getString(R.string.address_error_wrong_characters) } returns expected[0]

        //WHEN
        val result = validatorError.map { mapper.mapAddressError(it) }

        //THEN
        assertEquals(result, expected)
    }

}