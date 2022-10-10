package com.example.contactsapp.data

import android.content.Context
import com.example.contactsapp.data.contacts_api.ContactsAPI
import com.example.contactsapp.data.contacts_api.ContactsApiResponse
import com.example.contactsapp.entities.ContactResponse
import com.example.contactsapp.utilities.NetworkManager
import kotlinx.coroutines.*

class ContactsRepositoryNetworkRequestImpl(
    private val contactsApi: ContactsAPI,
    private val context: Context
) : ContactsRepository {

    override suspend fun getContactsList(): List<ContactResponse>? {
        return if (NetworkManager.isNetworkAvailable(context)){
            contactsApi.getContacts().data
        } else {
            null
        }

    }
}
