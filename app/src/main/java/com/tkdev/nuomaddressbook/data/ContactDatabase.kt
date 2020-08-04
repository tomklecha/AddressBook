package com.tkdev.nuomaddressbook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tkdev.nuomaddressbook.utilities.TextUtilities

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {

        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(
            context: Context
        ): ContactDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    TextUtilities.CONTACT_DATABASE
                )
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}
