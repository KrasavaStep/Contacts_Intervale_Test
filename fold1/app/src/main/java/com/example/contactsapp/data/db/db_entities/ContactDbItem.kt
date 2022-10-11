package com.example.contactsapp.data.db.db_entities

import androidx.room.PrimaryKey
import androidx.room.Entity
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Contacts")
data class ContactDbItem(
    @PrimaryKey val contactId: Int? = null,
    val contactName: String,
    val contactLastname: String,
    val contactPhoto: String,
    val contactEmail: String? = null,
    val contactPhone: String? = null
)
