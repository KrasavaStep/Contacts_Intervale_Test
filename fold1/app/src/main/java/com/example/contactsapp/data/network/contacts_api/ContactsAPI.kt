package com.example.contactsapp.data.network.contacts_api

import retrofit2.http.GET

interface ContactsAPI {

    @GET("users")
    suspend fun getContacts(): ContactsApiResponse

    companion object {
        const val CONTACTS_API_URL = "https://dummyjson.com/"
    }

}
