package com.bernardooechsler.contactsv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter(private val contacts: ArrayList<Contact>) :
    RecyclerView.Adapter<ContactsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }
}

class ContactsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textViewContactName = view.findViewById<TextView>(R.id.text_view_contact_name)

    fun bind(contacts: Contact) {

        textViewContactName.text = contacts.name
    }
}