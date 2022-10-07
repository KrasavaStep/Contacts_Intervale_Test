package com.example.contactsapp.entities

import com.google.gson.annotations.SerializedName

data class JsonContactResponse(
    @SerializedName("photo") val photo: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("phone_number") var phoneNumber: String? = null,
    @SerializedName("email") var email: String? = null
)