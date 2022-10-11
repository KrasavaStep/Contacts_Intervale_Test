package com.example.contactsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsapp.data.db.db_entities.ContactDbItem

@Database(entities = [ContactDbItem::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun getContactsDao() : ContactsDAO
}
