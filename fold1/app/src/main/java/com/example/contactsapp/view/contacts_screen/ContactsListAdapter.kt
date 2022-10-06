package com.example.contactsapp.view.contacts_screen

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.ContactListItemBinding
import com.example.contactsapp.entities.ContactItem


class ContactsListAdapter : ListAdapter<ContactItem, ContactsListAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ContactListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContactsListAdapter.ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.binding.apply {
            nameSurname.text = contact.name + " " + contact.surname
            email.text = contact.email
            phone.text = contact.phoneNumber

            contactImage.setImageBitmap(contact.photo)

        }
    }

    fun setData(contacts: List<ContactItem>) {
        submitList(contacts.toMutableList())
    }

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<ContactItem> =
            object : DiffUtil.ItemCallback<ContactItem>() {
                override fun areItemsTheSame(
                    oldItem: ContactItem,
                    newItem: ContactItem
                ): Boolean {
                    return oldItem.name == newItem.name
                            && oldItem.surname == newItem.surname
                            && oldItem.phoneNumber == newItem.phoneNumber
                            && oldItem.email == newItem.email
                }

                override fun areContentsTheSame(
                    oldItem: ContactItem,
                    newItem: ContactItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}