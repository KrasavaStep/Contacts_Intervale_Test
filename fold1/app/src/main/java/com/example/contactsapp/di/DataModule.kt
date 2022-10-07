package com.example.contactsapp.di

import com.example.contactsapp.data.contacts_api.ContactsAPI
import com.example.contactsapp.data.ContactsRepositoryBuildInJsonImpl
import com.example.contactsapp.data.ContactsRepositoryNetworkRequestImpl
import org.koin.dsl.module

val dataModule = module {

    single<ContactsRepositoryBuildInJsonImpl> {
        ContactsRepositoryBuildInJsonImpl(get())
    }

    single<ContactsRepositoryNetworkRequestImpl> {
        ContactsRepositoryNetworkRequestImpl(get<ContactsAPI>())
    }
}