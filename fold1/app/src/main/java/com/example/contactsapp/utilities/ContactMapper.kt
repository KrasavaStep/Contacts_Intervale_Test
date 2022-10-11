package com.example.contactsapp.utilities

import com.example.contactsapp.data.db.db_entities.ContactDbItem
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
                    lastname = contact.surname
                )
            )
        }
        return contactList
    }

    fun fromDbObjectToContactItem(list: List<ContactDbItem>): List<ContactItem> {
        val contactList = mutableListOf<ContactItem>()
        list.forEach { contact ->
            contactList.add(
                ContactItem(
                    photo = contact.contactPhoto,
                    email = contact.contactEmail ?: "",
                    phoneNumber = contact.contactPhone ?: "",
                    name = contact.contactName,
                    lastname = contact.contactLastname
                )
            )
        }
        return contactList
    }

    fun fromContactItemToDbObject(contact: ContactItem): ContactDbItem {
        return ContactDbItem(
            contactName = contact.name,
            contactLastname = contact.lastname,
            contactPhoto = contact.photo,
            contactEmail = contact.email,
            contactPhone = contact.phoneNumber
        )
    }

}
