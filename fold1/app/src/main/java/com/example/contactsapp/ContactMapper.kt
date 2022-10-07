package com.example.contactsapp

import com.example.contactsapp.entities.ContactItem
import com.example.contactsapp.entities.ContactResponse

class ContactMapper {

    fun fromJSONResponseToContactItem(list: List<ContactResponse>): List<ContactItem> {
        val contactList = mutableListOf<ContactItem>()
        list.forEach { contact ->
            contactList.add(
                ContactItem(
                    photo = contact.photo,
                    email = contact.email ?: "",
                    phoneNumber = contact.phoneNumber ?: "",
                    name = contact.name,
                    surname = contact.surname
                )
            )
        }
        return contactList
    }


}