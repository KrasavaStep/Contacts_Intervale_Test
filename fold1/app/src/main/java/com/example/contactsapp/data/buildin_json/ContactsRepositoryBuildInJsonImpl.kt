package com.example.contactsapp.data.buildin_json

import android.content.Context
import android.util.Log
import com.example.contactsapp.utilities.ContactMapper
import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.entities.ContactItem
import com.example.contactsapp.entities.ContactResponse
import com.example.contactsapp.utilities.NotYetImplementedException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

const val CONTACTS_FILE = "contacts.json"

class ContactsRepositoryBuildInJsonImpl(private val context: Context) : ContactsRepository {

    override suspend fun getContactsList(): List<ContactItem> {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open(CONTACTS_FILE)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e("data_parse", ioException.message.toString())
        }

        val listContactType = object : TypeToken<List<ContactResponse>>() {}.type

        return ContactMapper().fromJSONResponseToContactItem(
            Gson().fromJson(
                jsonString,
                listContactType
            )
        )
    }

    override suspend fun addContact(contact: ContactItem) {
        throw NotYetImplementedException("Not yet implemented")
    }
}
