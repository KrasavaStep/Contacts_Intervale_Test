package com.example.contactsapp.di

import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.data.db.ContactsDAO
import com.example.contactsapp.data.db.ContactsRepositoryDbImpl
import com.example.contactsapp.data.network.contacts_api.ContactsAPI
import com.example.contactsapp.data.network.ContactsRepositoryNetworkRequestImpl
import org.koin.dsl.module

val dataModule = module {
//    single<ContactsRepository> {
//        ContactsRepositoryBuildInJsonImpl(get())
//    }

//    single<ContactsRepository> {
//        ContactsRepositoryNetworkRequestImpl(get<ContactsAPI>(), get())
//    }

    single<ContactsRepository> {
        ContactsRepositoryDbImpl(get<ContactsDAO>())
    }
}
