package com.tkdev.nuomaddressbook.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "address") val address: String
) {
    constructor(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        address: String
    ) : this(0, firstName, lastName, email, phoneNumber, address)
}