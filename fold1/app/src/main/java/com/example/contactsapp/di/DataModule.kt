package com.example.contactsapp.di

import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.data.contacts_api.ContactsAPI
import com.example.contactsapp.data.ContactsRepositoryBuildInJsonImpl
import com.example.contactsapp.data.ContactsRepositoryNetworkRequestImpl
import org.koin.dsl.module

val dataModule = module {
//    single<ContactsRepository> {
//        ContactsRepositoryBuildInJsonImpl(get())
//    }

    single<ContactsRepository> {
        ContactsRepositoryNetworkRequestImpl(get<ContactsAPI>(), get())
    }
}
