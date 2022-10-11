package com.example.contactsapp.view.add_contact_screen

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentAddContactBinding
import com.example.contactsapp.entities.ContactItem
import com.example.contactsapp.view.contacts_screen.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class AddContactFragment : Fragment(R.layout.fragment_add_contact) {

    private var _binding: FragmentAddContactBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<AddContactsViewModel>(named("AddContactsVM"))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding = FragmentAddContactBinding.bind(view)
        binding.apply {

            val viewMap = mutableMapOf<String, View>()
            viewMap["name"] = editName
            viewMap["lastname"] = editLastname
            viewMap["phone"] = editPhone
            viewMap["email"] = editEmail
            viewMap["photo"] = editPhoto

            addContactBtn.setOnClickListener {
                for ((k, v) in viewMap) {
                    v.background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.edit_text_border,
                        null
                    )
                }
                viewModel.addContactToDb(createContact(), viewMap)
            }

            viewModel.errorViewLiveData.observe(viewLifecycleOwner) { list ->
                list.forEach { view ->
                    view.background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.edit_text_err_border,
                        null
                    )
                }
            }

            viewModel.errorTextLiveData.observe(viewLifecycleOwner) {
                errorText.text = it
            }

            viewModel.isDataSetLiveData.observe(viewLifecycleOwner) {
                if (it)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_addContactFragment_to_contactsListFragment)
            }
        }
    }

    private fun createContact(): ContactItem {
        binding.apply {
            return ContactItem(
                name = editName.text.toString(),
                lastname = editLastname.text.toString(),
                phoneNumber = editPhone.text.toString(),
                email = editEmail.text.toString(),
                photo = editPhoto.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
