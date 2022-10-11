package com.example.contactsapp.data.network

import android.content.Context
import com.example.contactsapp.utilities.ContactMapper
import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.data.network.contacts_api.ContactsAPI
import com.example.contactsapp.entities.ContactItem
import com.example.contactsapp.utilities.NetworkManager
import com.example.contactsapp.utilities.NotYetImplementedException

class ContactsRepositoryNetworkRequestImpl(
    private val contactsApi: ContactsAPI,
    private val context: Context
) : ContactsRepository {

    override suspend fun getContactsList(): List<ContactItem>? {
        return if (NetworkManager.isNetworkAvailable(context)){
            ContactMapper().fromJSONResponseToContactItem(contactsApi.getContacts().data)
        } else {
            null
        }

    }

    override suspend fun addContact(contact: ContactItem) {
        throw NotYetImplementedException("Not yet implemented")
    }
}
