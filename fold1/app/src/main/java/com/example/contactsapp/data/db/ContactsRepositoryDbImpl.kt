package com.example.contactsapp.data.db

import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.data.db.db_entities.ContactDbItem
import com.example.contactsapp.entities.ContactItem
import com.example.contactsapp.utilities.ContactMapper

class ContactsRepositoryDbImpl(private val contactsDao: ContactsDAO) : ContactsRepository {

    override suspend fun getContactsList(): List<ContactItem> {
        return ContactMapper().fromDbObjectToContactItem(contactsDao.getContactsFromDb())
    }

    override suspend fun addContact(contact: ContactItem) {
        val contactItem = ContactMapper().fromContactItemToDbObject(contact)
        contactsDao.addContactToDb(contactItem)
    }

}