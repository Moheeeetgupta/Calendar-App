package com.projects.calender.utils

import java.util.Calendar

object Utility {
    // Helper functions to calculate the days in the month and the day of the week for the first day of the month
    fun getDaysInMonth(month: Int, year: Int): Int {
        return when (month) {
            2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }
    }

    fun getFirstDayOfWeek(year: Int, month: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.get(Calendar.DAY_OF_WEEK) - 1
    }
}