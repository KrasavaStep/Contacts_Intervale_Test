package com.example.contactsapp.entities

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class ContactItem(
    @SerializedName("photo") val photo: Bitmap? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("surname") val surname: String? = null,
    @SerializedName("phone_number") val phoneNumber: String? = null,
    @SerializedName("email") val email: String? = null
)