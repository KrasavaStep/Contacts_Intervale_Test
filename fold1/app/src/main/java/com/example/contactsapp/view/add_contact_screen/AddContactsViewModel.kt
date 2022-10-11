package com.example.contactsapp.view.add_contact_screen

import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.entities.ContactItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class AddContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    private var _errorViewLiveData = MutableLiveData<List<View>>()
    val errorViewLiveData: LiveData<List<View>> = _errorViewLiveData

    private var _errorTextLiveData = MutableLiveData<String>()
    val errorTextLiveData: LiveData<String> = _errorTextLiveData

    private var _isDataSetLiveData = MutableLiveData<Boolean>()
    val isDataSetLiveData: LiveData<Boolean> = _isDataSetLiveData

    enum class InputErrors {
        EMPTY_FIELDS {
            override fun getErrorPlaces(
                contact: ContactItem,
                viewMap: Map<String, View>
            ): List<View> {
                val viewList = mutableListOf<View>()
                if (contact.name.isEmpty()) {
                    viewList.add(viewMap["name"]!!)
                }
                if (contact.lastname.isEmpty()) {
                    viewList.add(viewMap["lastname"]!!)
                }
                if (contact.photo.isEmpty()) {
                    viewList.add(viewMap["photo"]!!)
                }
                if (contact.email.isEmpty() && contact.phoneNumber.isEmpty()) {
                    viewList.add(viewMap["email"]!!)
                    viewList.add(viewMap["phone"]!!)
                }
                return viewList
            }
        },
        INCORRECT_EMAIL_PHONE {
            override fun getErrorPlaces(
                contact: ContactItem,
                viewMap: Map<String, View>
            ): List<View> {
                val viewList = mutableListOf<View>()
                if (contact.phoneNumber.isNotEmpty() && !Patterns.PHONE.matcher(contact.phoneNumber)
                        .matches()
                ) {
                    viewList.add(viewMap["phone"]!!)
                }
                if (contact.email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(contact.email)
                        .matches()
                ) {
                    viewList.add(viewMap["email"]!!)
                }
                return viewList
            }
        },
        INCORRECT_NAME_LENGTH {
            override fun getErrorPlaces(
                contact: ContactItem,
                viewMap: Map<String, View>
            ): List<View> {
                val viewList = mutableListOf<View>()
                if (contact.name.isNotEmpty() && contact.name.length !in 2..32) {
                    viewList.add(viewMap["name"]!!)
                }
                if (contact.lastname.isNotEmpty() && contact.lastname.length !in 2..32) {
                    viewList.add(viewMap["lastname"]!!)
                }
                return viewList
            }
        };

        abstract fun getErrorPlaces(contact: ContactItem, viewMap: Map<String, View>): List<View>

    }

    fun addContactToDb(contact: ContactItem, viewMap: Map<String, View>) {
        viewModelScope.launch(Dispatchers.IO) {
            val emptyFieldsList = InputErrors.EMPTY_FIELDS.getErrorPlaces(contact, viewMap)
            val incorrectMailPhone =
                InputErrors.INCORRECT_EMAIL_PHONE.getErrorPlaces(contact, viewMap)
            val incorrectName = InputErrors.INCORRECT_NAME_LENGTH.getErrorPlaces(contact, viewMap)

            if (emptyFieldsList.isNotEmpty() || incorrectMailPhone.isNotEmpty() || incorrectName.isNotEmpty()) {
                val errorViewList = concatenate(emptyFieldsList, incorrectMailPhone, incorrectName)
                val errorList = mutableListOf<String>()
                if (emptyFieldsList.isNotEmpty()) {
                    errorList.add("Please, fill out all fields!")
                }
                if (incorrectName.isNotEmpty()) {
                    errorList.add("Name and surname must contain from 2 to 32 characters")
                }
                if (incorrectMailPhone.isNotEmpty()) {
                    errorList.add("Incorrect mail or phone format")
                }
                _errorTextLiveData.postValue(errorList.joinToString("\n"))
                _errorViewLiveData.postValue(errorViewList)
                _isDataSetLiveData.postValue(false)
            } else {
                _isDataSetLiveData.postValue(true)
                repository.addContact(contact)
            }
        }
    }

    private fun <T> concatenate(vararg list: List<T>): List<T> {
        return listOf(*list).flatten()
    }


}
