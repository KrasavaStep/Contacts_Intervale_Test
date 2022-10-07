package com.example.contactsapp.data

import com.example.contactsapp.data.contacts_api.ContactsAPI
import com.example.contactsapp.data.contacts_api.ContactsApiResponse
import com.example.contactsapp.entities.ContactResponse
import kotlinx.coroutines.*

class ContactsRepositoryNetworkRequestImpl(
    private val contactsApi: ContactsAPI
) : ContactsRepository {

    override fun getContactsList(): List<ContactResponse> {
        var contactList = listOf<ContactResponse>()

        runBlocking {
            val list = async { getContactFromCoroutine().data }
            runBlocking {
                contactList = list.await()
            }
        }
        return contactList

    }

    private suspend fun getContactFromCoroutine(): ContactsApiResponse {
        return contactsApi.getContacts()
    }
}
