package com.example.contactsapp.view.add_contact_screen

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.ContactsRepository
import com.example.contactsapp.entities.ContactItem
import com.example.contactsapp.utilities.NotYetImplementedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    private val _nameTextErrorLiveData = MutableLiveData<String>()
    val nameTextErrorLiveData: LiveData<String> = _nameTextErrorLiveData

    private val _lastnameTextErrorLiveData = MutableLiveData<String>()
    val lastnameTextErrorLiveData: LiveData<String> = _lastnameTextErrorLiveData

    private val _photoTextErrorLiveData = MutableLiveData<String>()
    val photoTextErrorLiveData: LiveData<String> = _photoTextErrorLiveData

    private val _emailTextErrorLiveData = MutableLiveData<String>()
    val emailTextErrorLiveData: LiveData<String> = _emailTextErrorLiveData

    private val _phoneTextErrorLiveData = MutableLiveData<String>()
    val phoneTextErrorLiveData: LiveData<String> = _phoneTextErrorLiveData

    private val _isDataSet = MutableLiveData<Boolean>()
    val isDataSet: LiveData<Boolean> = _isDataSet

    val contactName = MutableLiveData<String>()
    val contactLastname = MutableLiveData<String>()
    val contactEmail = MutableLiveData<String>()
    val contactPhone = MutableLiveData<String>()
    val contactPhoto = MutableLiveData<String>()

    private fun validateNameInput(name: String): Boolean {
        return if (name.isEmpty()) {
            _nameTextErrorLiveData.postValue("Fill out field!")
            false
        } else if (name.length !in 2..32) {
            _nameTextErrorLiveData.postValue("Name must be contain from 2 to 32 characters")
            false
        } else {
            _nameTextErrorLiveData.postValue("")
            true
        }
    }

    private fun validateLastnameInput(lastname: String): Boolean {
        return if (lastname.isEmpty()) {
            _lastnameTextErrorLiveData.postValue("Fill out field!")
            false
        } else if (lastname.length !in 2..32) {
            _lastnameTextErrorLiveData.postValue("Lastname must be contain from 2 to 32 characters")
            false
        } else {
            _lastnameTextErrorLiveData.postValue("")
            true
        }
    }

    private fun validatePhotoInput(photo: String): Boolean {
        return if (photo.isEmpty()) {
            _photoTextErrorLiveData.postValue("Fill out field!")
            false
        } else {
            _photoTextErrorLiveData.postValue("")
            true
        }
    }

    private fun validateEmailInput(email: String): Boolean {
        return if (email.isEmpty()) {
            _emailTextErrorLiveData.postValue("Fill out field!")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailTextErrorLiveData.postValue("Incorrect Email format")
            false
        } else {
            _emailTextErrorLiveData.postValue("")
            true
        }
    }

    private fun validatePhoneInput(phone: String): Boolean {
        return if (phone.isEmpty()) {
            _phoneTextErrorLiveData.value = "Fill out field!"
            false
        } else if (!Patterns.PHONE.matcher(phone).matches() && phone.length < 9) {
            _phoneTextErrorLiveData.value = "Incorrect phone number"
            false
        } else {
            _phoneTextErrorLiveData.value = ""
            true
        }
    }

    private fun validateAllFields(contact: ContactItem): Boolean {

        val isNameValid = validateNameInput(contact.name)
        val isLastnameValid = validateLastnameInput(contact.lastname)
        val isPhotoValid = validatePhotoInput(contact.photo)
        validateEmailInput(contact.email)
        validatePhoneInput(contact.phoneNumber)

        var isMailAndPhoneValid = false

        if (contact.email.isNotEmpty()) {
            isMailAndPhoneValid = validateEmailInput(contact.email)
            _phoneTextErrorLiveData.postValue("")
        } else if (contact.phoneNumber.isNotEmpty()) {
            isMailAndPhoneValid = validatePhoneInput(contact.phoneNumber)
            _emailTextErrorLiveData.postValue("")
        } else if (contact.email.isNotEmpty() && contact.phoneNumber.isNotEmpty()) {
            isMailAndPhoneValid =
                validatePhoneInput(contact.phoneNumber) && validateEmailInput(contact.email)
        }
        return isMailAndPhoneValid && isNameValid && isLastnameValid && isPhotoValid
    }

    fun addContact(
        name: String?,
        lastname: String?,
        email: String?,
        photo: String?,
        phone: String?
    ) {
        val newContact = ContactItem(
            name = name ?: "",
            lastname = lastname ?: "",
            email = email ?: "",
            photo = photo ?: "",
            phoneNumber = phone ?: ""
        )

        if (validateAllFields(newContact)) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    repository.addContact(newContact)
                    _isDataSet.postValue(true)
                } catch (e: NotYetImplementedException) {
                    Log.d("AppException", "${e.message}")
                    _isDataSet.postValue(false)
                } catch (e: Exception) {
                    Log.d("AppException", "${e.message}")
                    _isDataSet.postValue(false)
                }
            }
        }
    }

}
