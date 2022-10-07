package com.example.contactsapp.di

import com.example.contactsapp.data.ContactsRepository
import org.koin.dsl.module

val dataModule = module {
    single<ContactsRepository> {
        ContactsRepository(get())
    }
}