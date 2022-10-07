package com.example.contactsapp.di

import com.example.contactsapp.data.ContactsRepositoryBuildInJsonImpl
import org.koin.dsl.module

val dataModule = module {
    single<ContactsRepositoryBuildInJsonImpl> {
        ContactsRepositoryBuildInJsonImpl(get())
    }
}