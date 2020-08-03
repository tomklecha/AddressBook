package com.tkdev.nuomaddressbook

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.data.ContactDao
import com.tkdev.nuomaddressbook.data.ContactDatabase
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactDatabaseTest {

    private lateinit var contactDatabase: ContactDatabase
    private lateinit var contactDao: ContactDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        contactDatabase = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java).build()
        contactDao = contactDatabase.contactDao()
    }

    @Test
    fun insertContact() {
        //GIVEN
        val contact = Contact("Tom", "Klecha", "Tom@gmail.com", "07446", "Poland")
        val expected = 1

        //WHEN
        contactDao.insert(contact)
        val result = contactDao.getContacts().size

        //THEN
        assertEquals(expected, result)

    }

    @Test
    fun insertListOfContacts() {
        //GIVEN
        val contacts =
            listOf(
                Contact(0, "Tom", "Klecha", "Tom@gmail.com", "07446", "Poland"),
                Contact(0, "Dom", "Roberston", "Dom@gmail.com", "654123", "UK"),
                Contact(0, "Bob", "Burnquist", "Bob@gmail.com", "111555", "Brasil")
            )
        val expected = 3

        //WHEN
        contactDao.insertAll(contacts)
        val result = contactDao.getContacts().size

        //THEN
        assertEquals(expected, result)

    }

    @Test
    fun checkContactId() {
        //GIVEN
        val contacts =
            listOf(
                Contact("Tom", "Klecha", "Tom@gmail.com", "07446", "Poland"),
                Contact("Dom", "Roberston", "Dom@gmail.com", "654123", "UK"),
                Contact("Bob", "Burnquist", "Bob@gmail.com", "111555", "Brasil")
            )
        val expected = 3

        //WHEN
        contactDao.insertAll(contacts)
        val result = contactDao.getContacts()[2].id

        //THEN
        assertEquals(expected, result)

    }

    @Test
    fun checkContactNameById() {
        //GIVEN
        val contacts =
            listOf(
                Contact("Tom", "Klecha", "Tom@gmail.com", "07446", "Poland"),
                Contact("Dom", "Roberston", "Dom@gmail.com", "654123", "UK"),
                Contact("Bob", "Burnquist", "Bob@gmail.com", "111555", "Brasil")
            )
        val expected = "Bob"
        
        //WHEN
        contactDao.insertAll(contacts)
        val result = contactDao.getContacts(3).firstName

        //THEN
        assertEquals(expected, result)

    }

}