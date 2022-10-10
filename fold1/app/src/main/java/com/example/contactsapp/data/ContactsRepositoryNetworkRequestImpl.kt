package com.example.contactsapp.data

import com.example.contactsapp.data.contacts_api.ContactsAPI
import com.example.contactsapp.data.contacts_api.ContactsApiResponse
import com.example.contactsapp.entities.ContactResponse
import kotlinx.coroutines.*

class ContactsRepositoryNetworkRequestImpl(
    private val contactsApi: ContactsAPI
) : ContactsRepository {

    override suspend fun getContactsList(): List<ContactResponse> {
        return contactsApi.getContacts().data
    }
}
