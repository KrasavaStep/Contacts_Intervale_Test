package com.example.contactsapp.data

import android.content.Context
import android.util.Log
import com.example.contactsapp.entities.ContactItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

const val CONTACTS_FILE = "contacts.json"

class ContactsRepository(private val context: Context) {

    fun getContactsList() : List<ContactItem>{

        lateinit var jsonString: String
        try{
            jsonString = context.assets.open(CONTACTS_FILE)
                .bufferedReader()
                .use { it.readText() }
        }
        catch (ioException: IOException){
            Log.e("data_parse", ioException.message.toString())
        }

        val listContactType = object : TypeToken<List<ContactItem>>(){}.type
        return Gson().fromJson(jsonString, listContactType)
    }

}