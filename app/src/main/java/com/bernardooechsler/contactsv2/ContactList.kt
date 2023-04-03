package com.bernardooechsler.contactsv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var contactList = arrayListOf(
        Contact("Bernardo"),
        Contact("Ana Julia"),
        Contact("Claudia"),
        Contact("Cesar"),
        Contact("Dokie"),
        Contact("Dokie"),
        Contact("Dokie"),
        Contact("Dokie"),
        Contact("Dokie"),
        Contact("Dokie"),
        Contact("Dokie")
    )

    private val adapter: ContactListAdapter = ContactListAdapter(contactList, ::openContactDetailView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_contact)

        val recyclerViewContacts: RecyclerView = findViewById(R.id.recycler_view_contact_list)
        recyclerViewContacts.adapter = adapter
    }

    private fun openContactDetailView(contact: Contact) {
        val intent = Intent(this, ContactDetailActivity::class.java)
            .apply {
                putExtra(ContactDetailActivity.CONTACT_NAME_EXTRA, contact.name)
            }
        startActivity(intent)
    }
}