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

    val nameTextLD = MutableLiveData("")
    private val _nameTextErrorLD = MutableLiveData<String>()
    val nameTextErrorLD: LiveData<String> = _nameTextErrorLD

    val lastnameTextLD = MutableLiveData("")
    private val _lastnameTextErrorLD = MutableLiveData<String>()
    val lastnameTextErrorLD: LiveData<String> = _lastnameTextErrorLD

    val photoTextLD = MutableLiveData("")
    private val _photoTextErrorLD = MutableLiveData<String>()
    val photoTextErrorLD: LiveData<String> = _photoTextErrorLD

    val emailTextLD = MutableLiveData("")
    private val _emailTextErrorLD = MutableLiveData<String>()
    val emailTextErrorLD: LiveData<String> = _emailTextErrorLD

    val phoneTextLD = MutableLiveData("")
    private val _phoneTextErrorLD = MutableLiveData<String>()
    val phoneTextErrorLD: LiveData<String> = _phoneTextErrorLD

    private val _isDataSet = MutableLiveData(false)
    val isDataSet: LiveData<Boolean> = _isDataSet

    private fun validateNameInput(name: String): Boolean {
        return if (name.isEmpty()) {
            _nameTextErrorLD.postValue("Fill out field!")
            false
        } else if (name.length !in 2..32) {
            _nameTextErrorLD.postValue("Name must be contain from 2 to 32 characters")
            false
        } else {
            _nameTextErrorLD.postValue("")
            true
        }
    }

    private fun validateLastnameInput(lastname: String): Boolean {
        return if (lastname.isEmpty()) {
            _lastnameTextErrorLD.postValue("Fill out field!")
            false
        } else if (lastname.length !in 2..32) {
            _lastnameTextErrorLD.postValue("Lastname must be contain from 2 to 32 characters")
            false
        } else {
            _lastnameTextErrorLD.postValue("")
            true
        }
    }

    private fun validatePhotoInput(photo: String): Boolean {
        return if (photo.isEmpty()) {
            _photoTextErrorLD.postValue("Fill out field!")
            false
        } else {
            _photoTextErrorLD.postValue("")
            true
        }
    }

    private fun validateEmailInput(email: String): Boolean {
        return if (email.isEmpty()) {
            _emailTextErrorLD.postValue("Fill out field!")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailTextErrorLD.postValue("Incorrect Email format")
            false
        } else {
            _emailTextErrorLD.postValue("")
            true
        }
    }

    private fun validatePhoneInput(phone: String): Boolean {
        return if (phone.isEmpty()) {
            _phoneTextErrorLD.value = "Fill out field!"
            Log.d("err_b", "empty")
            false
        } else if (!Patterns.PHONE.matcher(phone).matches() && phone.length < 9) {
            Log.d("err_b", "not match")
            _phoneTextErrorLD.value = "Incorrect phone number"
            false
        } else {
            Log.d("err_b", "all right")
            _phoneTextErrorLD.value = ""
            true
        }
    }

    private fun validateAllFields(contact: ContactItem): Boolean {
        val isMailValid = validateEmailInput(contact.email)
        val isNameValid = validateNameInput(contact.name)
        val isLastnameValid = validateLastnameInput(contact.lastname)
        val isPhoneValid = validatePhoneInput(contact.phoneNumber)
        val isPhotoValid = validatePhotoInput(contact.photo)
        return isMailValid && isNameValid && isLastnameValid && isPhoneValid && isPhotoValid
    }

    fun addContactToDb(contact: ContactItem) {
        val isValid = validateAllFields(contact)
        Log.d("err_b", isValid.toString())
        if (isValid) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addContact(contact)
                _isDataSet.postValue(true)
            }
        }
    }

}
