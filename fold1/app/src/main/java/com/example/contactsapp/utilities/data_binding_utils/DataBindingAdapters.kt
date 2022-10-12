package com.example.contactsapp.utilities.data_binding_utils

import android.util.Log
import androidx.databinding.BindingAdapter
import com.example.contactsapp.R
import com.google.android.material.textfield.TextInputLayout

class DataBindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("app:errorText")
        fun setErrorMessage(view: TextInputLayout?, errorMsg: String?) {
            errorMsg?.let {
                Log.d("err_b", "$errorMsg + erM")
                if (errorMsg.isEmpty()) {
                    view?.error = null
                    view?.editText?.setBackgroundResource(R.drawable.edit_text_border)
                } else {
                    view?.error = errorMsg
                    view?.editText?.setBackgroundResource(R.drawable.edit_text_err_border)
                }
            }
        }
    }
}