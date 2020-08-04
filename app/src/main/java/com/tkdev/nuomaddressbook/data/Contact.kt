package com.tkdev.nuomaddressbook.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tkdev.nuomaddressbook.utilities.TextUtilities

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TextUtilities.CONTACT_ID) val id: Int,

    @ColumnInfo(name = TextUtilities.CONTACT_FIRST_NAME) val firstName: String,
    @ColumnInfo(name = TextUtilities.CONTACT_LAST_NAME) val lastName: String,
    @ColumnInfo(name = TextUtilities.CONTACT_EMAIL) val email: String,
    @ColumnInfo(name = TextUtilities.CONTACT_PHONE_NUMBER) val phoneNumber: String,
    @ColumnInfo(name = TextUtilities.CONTACT_ADDRESS) val address: String
) {
    constructor(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        address: String
    ) : this(0, firstName, lastName, email, phoneNumber, address)
}