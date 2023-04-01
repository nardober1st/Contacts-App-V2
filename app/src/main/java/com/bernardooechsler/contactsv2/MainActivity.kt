package com.bernardooechsler.contactsv2

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

    private val adapter: ContactListAdapter = ContactListAdapter(contactList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewContacts: RecyclerView = findViewById(R.id.recycler_view_contact_list)
        recyclerViewContacts.adapter = adapter
    }
}