package com.example.contactsapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.contactsapp.entities.ContactItem
import com.example.contactsapp.entities.JsonContactResponse
import java.net.URL

class ContactMapper {

    fun fromJSONResponseToContactItem(list: List<JsonContactResponse>): List<ContactItem> {
        val contactList = mutableListOf<ContactItem>()
        list.forEach { contact ->
            contactList.add(
                ContactItem(
                    photo = fromURLtoBitmap(URL(contact.photo)),
                    email = contact.email ?: "email missing",
                    phoneNumber = contact.phoneNumber ?: "phone missing",
                    name = contact.name,
                    surname = contact.surname
                )
            )
        }
        return contactList
    }

    private fun fromURLtoBitmap(url: URL): Bitmap {
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }
}