package com.example.loginmvvm.common.date

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date
import java.util.Locale

object DateUtil {

    private const val DATE_BR = "dd/MM/yyyy"
    private const val DATE_USA = "yyyy-MM-dd"

    fun String.toDateAmerica(): String {
        return try {
            val format: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_BR)
            return LocalDate.parse(this, format).toString()
        }catch (e: DateTimeParseException) {
            this
        }
    }

    fun String.toDateBrazilian(): String {
        return try {
            val formatUS: DateFormat = SimpleDateFormat(DATE_USA, Locale.getDefault())
            val date: Date = formatUS.parse(this) as Date
            val formatBR: DateFormat = SimpleDateFormat(DATE_BR, Locale.getDefault())
            formatBR.format(date)
        }catch (e: DateTimeParseException) {
            this
        }
    }
}
