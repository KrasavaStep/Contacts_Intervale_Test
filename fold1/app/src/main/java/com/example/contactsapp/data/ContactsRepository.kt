package com.example.contactsapp.data

import com.example.contactsapp.entities.ContactResponse

interface ContactsRepository {
    suspend fun getContactsList(): List<ContactResponse>
}
