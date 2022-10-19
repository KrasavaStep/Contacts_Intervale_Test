package com.example.contactsapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsapp.data.db.ContactsDAO
import com.example.contactsapp.data.db.ContactsDatabase
import org.koin.dsl.module

private const val PREPOPULATE_DB = "prp_contacts_db.db"

fun provideDatabase(application: Application): ContactsDatabase {
    return Room.databaseBuilder(application, ContactsDatabase::class.java, "contacts_db")
        .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
        .createFromAsset(PREPOPULATE_DB)
        .build()
}

fun provideDao(database: ContactsDatabase): ContactsDAO {
    return database.getContactsDao()
}

val dbModule = module {
    single { provideDatabase(get()) }
    single { provideDao(get()) }
}
