package com.bernardooechsler.contactsv2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var contactList = arrayListOf(
        Contact(1, "Bernardo", "1 (650) 346-6474"),
        Contact(2, "Ana Julia", "1 (408) 376-1014"),
        Contact(3, "Claudia", "1 (650) 533-8815"),
        Contact(4, "Cesar", "1 (650) 274-8729")
    )

    private val adapter: ContactListAdapter = ContactListAdapter(::onListItemClicked)

    // This make sure that you are opening a activity and requesting a result
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Getting the result from ContactDetailActivity
            val data = result.data
            val contactAction = data?.getSerializableExtra(CONTACT_ACTION_RESULT) as ContactAction
            val contact: Contact = contactAction.contact

            if (contactAction.actionType == ActionType.DELETE.name) {
                val newList = arrayListOf<Contact>()
                    .apply {
                        addAll(contactList)
                    }
                // Removing contact from the list
                newList.remove(contact)
                // Update adapter (before was adapter.submit(contactList)
                adapter.submitList(newList)
                // Updating contactList with newList values
                contactList = newList

            } else if (contactAction.actionType == ActionType.CREATE.name) {
                val newList = arrayListOf<Contact>()
                    .apply {
                        addAll(contactList)
                    }
                newList.add(contact)

                adapter.submitList(newList)
                contactList = newList
            } else if (contactAction.actionType == ActionType.UPDATE.name) {
                val tempEmptyList = arrayListOf<Contact>()
                contactList.forEach {
                    if (it.id == contact.id) {
                        val newContact = Contact(
                            it.id,
                            contact.contactName,
                            contact.contactPhone
                        )
                        tempEmptyList.add(newContact)
                    } else {
                        tempEmptyList.add(it)
                    }
                }
                adapter.submitList(tempEmptyList)
                contactList = tempEmptyList
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_contact)

        // (before was adapter.submit(contactList)
        adapter.submitList(contactList)

        val recyclerViewContacts: RecyclerView = findViewById(R.id.recycler_view_contact_list)
        recyclerViewContacts.adapter = adapter

        val fabAdd = findViewById<FloatingActionButton>(R.id.floating_add_contact_button)
        fabAdd.setOnClickListener {
            openContactListDetail()
        }
    }

    private fun onListItemClicked(contact: Contact) {
        openContactListDetail(contact)
    }

    //                                Default Argument
    private fun openContactListDetail(contact: Contact? = null) {
        // Opening ContactDetailActivity and making sure a "contact is being passed"
        val intent = ContactDetailActivity.start(this, contact)
        startForResult.launch(intent)
    }
}

enum class ActionType {
    DELETE,
    UPDATE,
    CREATE
}

data class ContactAction(
    val contact: Contact,
    val actionType: String
) : Serializable

const val CONTACT_ACTION_RESULT = "CONTACT_ACTION_RESULT"