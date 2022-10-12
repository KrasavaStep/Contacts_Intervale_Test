package com.example.contactsapp.data.db.db_entities

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "Contacts")
data class ContactDbItem(
    @PrimaryKey(autoGenerate = true) val contactId: Int,
    val contactName: String,
    val contactLastname: String,
    val contactPhoto: String,
    val contactEmail: String? = null,
    val contactPhone: String? = null
)
