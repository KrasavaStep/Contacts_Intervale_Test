package com.example.contactsapp.data

import com.example.contactsapp.entities.ContactResponse

interface ContactsRepository {
    fun getContactsList() : List<ContactResponse>
}