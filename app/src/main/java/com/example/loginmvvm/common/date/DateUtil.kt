package com.example.loginmvvm.common.date

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtil {

    /** Format Explanation
    EEE : Day ( Mon )
    MMM : Month in words ( Dec )
    MM : Day in Count ( 324 )
    mm : Month ( 12 )
    dd : Date ( 3 )
    HH : Hours ( 12 )
    mm : Minutes ( 50 )
    ss : Seconds ( 34 )
    yyyy: Year ( 2020 ) //both yyyy and YYYY are same
    YYYY: Year ( 2020 )
    zzz : GMT+05:30
    aa : ( AM / PM )
     **/

    const val DATE_TIME_BR = "dd/MM/yyyy hh:mm:ss"
    const val DATE_BR = "dd/MM/yyyy"
    const val DATE_TIME_USA = "yyyy-MM-dd HH:mm:ss"
    const val DATE_USA = "yyyy-MM-dd"
    const val DATE_TIME_USA_GMT = "EEE MMM dd HH:mm:ss zzz yyyy"

    const val timeZoneBrSp = "America/Sao_Paulo"
    val localeBr = Locale("pt", "BR")
    val localeUs = Locale("en", "US")

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat(DATE_TIME_BR, Locale("pt", "BR"))
        sdf.timeZone = TimeZone.getTimeZone(timeZoneBrSp)
        return sdf.format(Date())
    }

    fun String.toDateAmerica(): String {
        return if (this.isNotEmpty()) {
            val format: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_BR)
            return LocalDate.parse(this, format).toString()
        } else ""
    }

    fun String.toDateTimeAmerica(): String {
        return if (this.isNotEmpty()) {
            val format: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_BR)
            return LocalDate.parse(this, format).toString()
        } else ""
    }

    fun String.toDateBrazilian(): String {
        return if (this.isNotEmpty()) {
            val formatUS: DateFormat = SimpleDateFormat(DATE_USA, Locale.getDefault())
            val date: Date = formatUS.parse(this) as Date

            val formatBR: DateFormat = SimpleDateFormat(DATE_BR, Locale.getDefault())
            return formatBR.format(date)
        } else ""
    }

    fun String.toFormatMoth(): String {
        return if (this.isNotEmpty()) {
            val date: Date? = SimpleDateFormat(DATE_TIME_USA, Locale.getDefault()).parse(this)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            val month: String =
                calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, localeBr)!!
            month.replace(".", "")
        } else ""
    }

    fun getCurrentMoth(): String {
        val calendar = Calendar.getInstance()
        return calendar.getDisplayName(
            Calendar.MONTH, Calendar.LONG, localeUs
        )?.lowercase() ?: ""
    }

    fun String.toFormatDayOfMoth(): String {
        return if (this.isNotEmpty()) {
            val date: Date? = SimpleDateFormat(DATE_TIME_USA, Locale.getDefault()).parse(this)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            calendar.get(Calendar.DAY_OF_MONTH).toString()
        } else ""
    }

    fun String.toFormatHour(): String {
        return try {
            val date: Date? = SimpleDateFormat(DATE_TIME_USA, Locale.getDefault()).parse(this)
            val calendar = Calendar.getInstance()
            calendar.time = date!!
            calendar.get(Calendar.HOUR_OF_DAY).toString() + ":" + calendar.get(Calendar.MINUTE)
                .toString()
        } catch (e: Exception) {
            ""
        }
    }

    fun String.toFormatDate(): String {
        return if (this.isNotEmpty()) {
            val formatUS: DateFormat = SimpleDateFormat(DATE_TIME_USA, Locale.getDefault())
            val date: Date = formatUS.parse(this) as Date

            val formatBR: DateFormat = SimpleDateFormat(DATE_BR, Locale.getDefault())
            return formatBR.format(date)
        } else ""
    }

    fun String.toMillisSeconds(): Long {
        return try {
            //Todo talvez o motivo do timer event não funcionar bem, seja por causa da hora HH que conta apenas 12 horas
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("pt", "BR"))
            formatter.timeZone = TimeZone.getTimeZone("America/Sao_Paulo")

            val c = Calendar.getInstance()
            c.time = formatter.parse(this)!!
            return  c.timeInMillis
        } catch (e: Exception) {
            0L
        }
    }

    fun Long.toDate(): String {
        return try {
            val formatter = getDateFormatBr(DATE_TIME_USA)
            formatter.format(Date(this))
        } catch (e: Exception) {
            ""
        }
    }

    fun String.convertToCustomFormat(formatWaited: String): String {
        return try {
            val sourceFormat = SimpleDateFormat(formatWaited, Locale.getDefault()).parse(this)
            val destFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            destFormat.format(sourceFormat!!)
        }catch (e: Exception) {
            ""
        }
    }

    fun getStatusDate(dateEvent: String): DateStatus {
        var date: Date? = null
        val today: Date
        val inputDateFormat = getDateFormatBr(DATE_TIME_USA)

        val millisCurrent = System.currentTimeMillis()
        val resultDate = Date(millisCurrent)
        val dt = inputDateFormat.format(resultDate)
        today = inputDateFormat.parse(dt)!!

        try {
            date = inputDateFormat.parse(dateEvent)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        if (date == null) return DateStatus.WRONG

        return when {
            date.before(today) -> {
                DateStatus.BEFORE
            }
            date.after(today) -> {
                DateStatus.AFTER
            }
            else -> {
                DateStatus.EQUAL
            }
        }
    }

    fun getDateFormatBr(format: String) : SimpleDateFormat {
        return SimpleDateFormat(format, localeBr).apply {
            timeZone = TimeZone.getTimeZone(timeZoneBrSp)
        }
    }

    enum class DateStatus {
        AFTER,
        BEFORE,
        EQUAL,
        WRONG
    }
}