package com.example.contactsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.contactsapp.data.db.db_entities.ContactDbItem

@Dao
interface ContactsDAO {

    @Query("SELECT * FROM contacts order by contactId")
    suspend fun getContactsFromDb() : List<ContactDbItem>

    @Insert
    suspend fun addContactToDb(contact: ContactDbItem)
}
