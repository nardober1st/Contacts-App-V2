package com.bernardooechsler.contactsv2

import java.io.Serializable

data class Contact(
    val id: Int,
    var contactName: String,
    var contactPhone: String
) : Serializable

