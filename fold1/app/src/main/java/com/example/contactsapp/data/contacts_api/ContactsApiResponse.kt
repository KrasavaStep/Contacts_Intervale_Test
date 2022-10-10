package com.example.contactsapp.data.contacts_api

import com.example.contactsapp.entities.ContactResponse
import com.google.gson.annotations.SerializedName

data class ContactsApiResponse(
    @SerializedName("users") val data: List<ContactResponse>
)
