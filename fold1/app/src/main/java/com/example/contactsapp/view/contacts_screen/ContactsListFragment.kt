package com.example.contactsapp.view.contacts_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentContactsListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ContactsListFragment : Fragment(R.layout.fragment_contacts_list) {

    private var _binding: FragmentContactsListBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<ContactsViewModel>(named("ContactsVM"))
    private lateinit var adapter: ContactsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ContactsListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentContactsListBinding.bind(view)
        binding.contactsRv.adapter = adapter
        binding.contactsRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getContactsList()

        viewModel.contactsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.contactsRv.visibility = View.VISIBLE
                binding.errorLayout.visibility = View.GONE
                adapter.setData(it)
            } ?: showErrorUi()

        }

        binding.refreshBtn.setOnClickListener {
            viewModel.getContactsList()
        }

        binding.toAddContactScreenBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_contactsListFragment_to_addContactFragment)
        }
    }

    private fun showErrorUi() {
        binding.apply {
            contactsRv.visibility = View.GONE
            errorLayout.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
