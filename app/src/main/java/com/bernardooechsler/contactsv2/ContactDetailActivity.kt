package com.bernardooechsler.contactsv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContactDetailActivity : AppCompatActivity() {

    companion object {
        val CONTACT_NAME_EXTRA = "contact.name.extra.detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val name: String = intent.getStringExtra(CONTACT_NAME_EXTRA) ?: ""
        val textViewContactName = findViewById<TextView>(R.id.text_view_contact_name)

        textViewContactName.text = name
    }
}