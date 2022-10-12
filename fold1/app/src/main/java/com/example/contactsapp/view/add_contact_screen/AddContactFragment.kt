package com.example.contactsapp.view.add_contact_screen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_contact, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            addContactBtn.setOnClickListener {
                viewModel.addContactToDb(createContact())
            }


            viewModel.isDataSet.observe(viewLifecycleOwner) {
                if (it)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_addContactFragment_to_contactsListFragment)
            }
        }
    }

    private fun createContact(): ContactItem {
        binding.apply {
            return ContactItem(
                name = editNameText.text.toString(),
                lastname = editLastnameText.text.toString(),
                phoneNumber = editPhoneText.text.toString(),
                email = editEmailText.text.toString(),
                photo = editPhotoText.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
