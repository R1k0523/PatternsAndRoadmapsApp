package ru.boringowl.parapp.presentation.repository

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        private val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH)
        fun currentDate(): String = sdf.format(Date())

        fun yesterdayDate(): String = sdf.format(getDaysAgo(7))

        private fun getDaysAgo(daysAgo: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
            return calendar.time
        }
    }
}