package com.jfagoaga.puellaagenda.utils

object ValidationUtils {
    fun isValidName(name: String): Boolean {
        return name.matches(Regex("^[a-zA-Z0-9\\s]+\$"))
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.matches(Regex("^[0-9]+\$"))
    }
}