package com.tkdev.nuomaddressbook.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.data.ContactsViewHolder
import com.tkdev.nuomaddressbook.databinding.ItemContactBinding

class ContactsAdapter(private val itemListener: ItemListener) :
    ListAdapter<Contact, ContactsViewHolder>(ContactDiffCallback()) {

    interface ItemListener {
        fun onItemClickListener(contactId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder =
        ContactsViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemListener
        )

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(
        oldItem: Contact,
        newItem: Contact
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Contact,
        newItem: Contact
    ): Boolean {
        return oldItem == newItem
    }
}