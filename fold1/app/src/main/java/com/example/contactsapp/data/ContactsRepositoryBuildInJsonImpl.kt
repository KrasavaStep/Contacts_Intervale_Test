package com.example.contactsapp.data

import android.content.Context
import android.util.Log
import com.example.contactsapp.entities.JsonContactResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

const val CONTACTS_FILE = "contacts.json"

class ContactsRepositoryBuildInJsonImpl(private val context: Context) : ContactsRepository{

    override fun getContactsList() : List<JsonContactResponse>{

        lateinit var jsonString: String
        try{
            jsonString = context.assets.open(CONTACTS_FILE)
                .bufferedReader()
                .use { it.readText() }
        }
        catch (ioException: IOException){
            Log.e("data_parse", ioException.message.toString())
        }

        val listContactType = object : TypeToken<List<JsonContactResponse>>(){}.type
        return Gson().fromJson(jsonString, listContactType)
    }

}