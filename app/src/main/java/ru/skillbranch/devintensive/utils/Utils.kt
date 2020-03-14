package ru.skillbranch.devintensive.utils

import android.provider.ContactsContract

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        //todo fix
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteratation(payload: String, divider: String = "") {
    }

    fun toInitials(firstName: String?, lastName: String?) {

    }
}