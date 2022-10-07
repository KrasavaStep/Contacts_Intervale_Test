package com.example.contactsapp.data

import com.example.contactsapp.entities.JsonContactResponse

interface ContactsRepository {
    fun getContactsList() : List<JsonContactResponse>
}