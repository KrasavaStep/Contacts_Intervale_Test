package com.example.contactsapp.data

import retrofit2.http.GET

interface ContactsAPI {

    @GET()
    suspend fun getUsers() : List<JsonContactResponse>

    
}