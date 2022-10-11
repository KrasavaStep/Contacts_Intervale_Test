package com.example.contactsapp.view.contacts_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ContactListItemBinding
import com.example.contactsapp.entities.ContactItem


class ContactsListAdapter :
    ListAdapter<ContactItem, ContactsListAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ContactListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        holder.binding.apply {
            nameSurname.text = contact.name + " " + contact.lastname
            if (contact.email.isEmpty()) {
                email.visibility = View.GONE
            }
            if (contact.phoneNumber.isEmpty()) {
                phone.visibility = View.GONE
            }
            email.text = contact.email
            phone.text = contact.phoneNumber

            Glide.with(holder.itemView)
                .asBitmap()
                .placeholder(R.drawable.empty_user)
                .error(R.drawable.empty_user)
                .load(contact.photo)
                .into(contactImage)
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
                            && oldItem.lastname == newItem.lastname
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
