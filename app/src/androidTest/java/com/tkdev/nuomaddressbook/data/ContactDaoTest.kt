package com.tkdev.nuomaddressbook.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.tkdev.nuomaddressbook.utilities.testContact
import com.tkdev.nuomaddressbook.utilities.testedContacts
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class ContactDaoTest {

    private lateinit var contactDatabase: ContactDatabase
    private lateinit var contactDao: ContactDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        contactDatabase = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java).build()
        contactDao = contactDatabase.contactDao()
    }

    @After
    fun closeDb() {
        contactDatabase.close()
    }

    @Test
    fun insertContact() {
        //GIVEN
        val contact = testContact
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
        val contacts = testedContacts
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
        val contacts = testedContacts
        val expected = 2

        //WHEN
        contactDao.insertAll(contacts)
        val result = contactDao.getContacts()[1].id

        //THEN
        assertEquals(expected, result)

    }

    @Test
    fun checkContactNameById() {
        //GIVEN
        val contacts = testedContacts
        val expected = "Bob"

        //WHEN
        contactDao.insertAll(contacts)
        val result = contactDao.getContact(3).firstName

        //THEN
        assertEquals(expected, result)

    }

}