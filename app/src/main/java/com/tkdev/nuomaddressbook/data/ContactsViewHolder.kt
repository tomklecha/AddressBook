package com.tkdev.nuomaddressbook.data

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.nuomaddressbook.adapters.ContactsAdapter
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsViewHolder(
    itemView: View,
    val listener: ContactsAdapter.Listener
) : RecyclerView.ViewHolder(itemView) {

    val name: TextView = itemView.contactName
    
    fun setCurrent(contact: Contact){
        itemView.setOnClickListener {
            listener.onItemSelected(contact.id)
        }

        name.text = contact.firstName
    }
}