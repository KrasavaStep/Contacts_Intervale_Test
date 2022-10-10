package com.example.contactsapp.entities

import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName(value = "photo", alternate = ["image"]) val photo: String,
    @SerializedName(value = "name", alternate = ["firstName"]) val name: String,
    @SerializedName(value = "surname", alternate = ["lastName"]) val surname: String,
    @SerializedName(value = "phone_number", alternate = ["phone"]) var phoneNumber: String? = null,
    @SerializedName("email") var email: String? = null
)
