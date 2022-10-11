package com.example.contactsapp.di

import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.view.contacts_screen.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    viewModel<ContactsViewModel>(named("ContactsVM")) {
        ContactsViewModel(get<ContactsRepository>())
    }
}
