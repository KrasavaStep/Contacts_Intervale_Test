package com.example.contactsapp.entities

import android.graphics.Bitmap

data class ContactItem(
    val photo: Bitmap,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String
)