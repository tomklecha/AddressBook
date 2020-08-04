package com.tkdev.nuomaddressbook.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.nuomaddressbook.R
import com.tkdev.nuomaddressbook.data.Contact
import com.tkdev.nuomaddressbook.data.ContactsViewHolder

class ContactsAdapter(context: Context, private val listener: Listener) : RecyclerView.Adapter<ContactsViewHolder>(){

    interface Listener {
        fun onItemSelected(contactId: Int)
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts: List<Contact> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder
        = ContactsViewHolder(inflater.inflate(R.layout.item_contact, parent, false), listener)

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.setCurrent(contacts[position])
    }

    fun setContactList(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

}