package com.tkdev.nuomaddressbook.data

import androidx.recyclerview.widget.RecyclerView
import com.tkdev.nuomaddressbook.adapters.ContactsAdapter
import com.tkdev.nuomaddressbook.databinding.ItemContactBinding

class ContactsViewHolder(
    private val binding: ItemContactBinding,
    private val itemListener: ContactsAdapter.ItemListener
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(contact: Contact) {
        binding.setClickListener {
            itemListener.onItemClickListener(contact.id)
        }
        binding.apply {
            this.contact = contact
            executePendingBindings()
        }
    }
}