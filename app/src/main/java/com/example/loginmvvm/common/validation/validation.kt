package com.example.loginmvvm.common.validation

import android.util.Log
import com.example.loginmvvm.common.string.StringUtil.handlerJustNumber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

fun String.isEmailValid() =
    Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    ).matcher(this).matches()

fun String.isPasswordValid() = this.length in 6..12

fun String.isNameValid(): Boolean {
    return try {
        val name = this.split(" ")
        name[0].length > 1 && name[1].isNotEmpty() && name[1].length > 1
    } catch (e: Exception) {
        false
    }
}

fun String.isBirthDateValid(): Boolean {
    if (this.length != 10) return false
    val validateDate = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
    validateDate.isLenient = false

    try {
        val dt = validateDate.parse(this)
        dt?.let {
            if (it.after(Date())) {
                return false
            }
        }

    } catch (pe: ParseException) {
        Log.e("DATE_ERROR", pe.message.toString())
        return false
    }
    return true
}

fun String.isCellPhoneValid(): Boolean {
    if (handlerJustNumber(this).length == 11) {
        if (handlerJustNumber(this) != "00000000000"
            && handlerJustNumber(this).startsWith("9", 2)
        )
            return true
    }
    return false
}