package com.tkdev.nuomaddressbook.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(contact: List<Contact>)

    @Query("SELECT * FROM contacts ORDER BY first_name ")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts where id IS :uid")
    fun getContact(uid: Int): Contact

}