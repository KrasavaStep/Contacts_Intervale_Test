package com.example.contactsapp.view.contacts_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.ContactMapper
import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.entities.ContactItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    private val _contactsLiveData = MutableLiveData<List<ContactItem>>()
    val contactsLiveData: LiveData<List<ContactItem>> = _contactsLiveData

    fun getContactsList() {
        viewModelScope.launch(Dispatchers.Default) {
            val contactList =
                ContactMapper().fromJSONResponseToContactItem(repository.getContactsList())

            _contactsLiveData.postValue(contactList)
        }
    }
}