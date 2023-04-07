package com.bernardooechsler.contactsv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

// We are gonna remove : RecyclerView.Adapter<ContactsListViewHolder>() and replace to : ListAdapter<Contact, ContactsListViewHolder>(ContactListAdapter)
class ContactListAdapter(
    private val openContactDetailView: (contact: Contact) -> Unit
) : ListAdapter<Contact, ContactsListViewHolder>(ContactListAdapter) {

//    private var contact: List<Contact> = emptyList()
//
//    fun submit(list: List<Contact>) {
//        contact = list
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactsListViewHolder(view)
    }

//    override fun getItemCount(): Int {
//        return contact.size
//    }

    // Change contact[position] to getItem(position)
    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact, openContactDetailView)
    }

    companion object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.contactName == newItem.contactName &&
                    oldItem.contactPhone == newItem.contactPhone
        }
    }
}

class ContactsListViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val textViewContactName = view.findViewById<TextView>(R.id.text_view_contact)

    fun bind(
        contact: Contact,
        openContactDetailView: (contact: Contact) -> Unit
    ) {

        textViewContactName.text = contact.contactName
        view.setOnClickListener {
            openContactDetailView.invoke(contact)
        }
    }
}