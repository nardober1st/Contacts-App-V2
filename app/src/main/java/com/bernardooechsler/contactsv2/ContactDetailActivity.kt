package com.bernardooechsler.contactsv2

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ContactDetailActivity : AppCompatActivity() {

    private var contact: Contact? = null
    private lateinit var btnAddUpdate: Button

    companion object {
        private const val CONTACT_EXTRA = "contact.extra.detail"

        // This function will make sure that whoever is opening ContactDetailActivity pass a "contact" to be shown
        fun start(context: Context, contact: Contact?): Intent {
            val intent = Intent(context, ContactDetailActivity::class.java)
                .apply {
                    putExtra(CONTACT_EXTRA, contact)
                }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        contact = intent.getSerializableExtra(CONTACT_EXTRA) as Contact?

        val edtName = findViewById<EditText>(R.id.edit_text_contact_name)
        val edtPhone = findViewById<EditText>(R.id.edit_text_phone_number)
        btnAddUpdate = findViewById(R.id.button_add_update)

        if (contact != null) {
            edtName.setText(contact!!.contactName)
            edtPhone.setText(contact!!.contactPhone)
        }

        btnAddUpdate.setOnClickListener {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                if (contact == null) {
                    addOrUpdateContact(0, name, phone, ActionType.CREATE)
                } else {
                    addOrUpdateContact(contact!!.id, name, phone, ActionType.UPDATE)
                }
            } else {
                Snackbar.make(it, "Fields are required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
        }

//        val textViewContactName = findViewById<TextView>(R.id.text_view_contact_name)
//        val textViewContactNumber = findViewById<TextView>(R.id.text_view_phone_number)
//
//        textViewContactName.text = contact?.contactName ?: "Add a contact"
//        textViewContactNumber.text = contact?.contactPhone
    }

    private fun addOrUpdateContact(
        id: Int,
        name: String,
        phone: String,
        actionType: ActionType
    ) {
        val contact = Contact(id, name, phone)
        returnAction(contact, actionType)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_contact_delete, menu)
        return true
    }

    // Check which option is selected to perform an action (Delete Contact)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_contact -> {

                if (contact != null) {
                    returnAction(contact!!, ActionType.DELETE)
                } else {
                    Snackbar.make(btnAddUpdate, "Fields are required", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun returnAction(contact: Contact, actionType: ActionType) {
        // Returning result to ContactList Activity
        val intent = Intent()
            .apply {
                val contactAction = ContactAction(contact, actionType.name)
                putExtra(CONTACT_ACTION_RESULT, contactAction)
            }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}