package com.projects.calender.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projects.calender.entities.WeekDays

import com.projects.calender.ui.theme.backgroundPrimary
import com.projects.calender.utils.Utility.getDaysInMonth
import com.projects.calender.utils.Utility.getFirstDayOfWeek


@Composable
fun MonthViewComposable(year: Int, month: Int, setTaskDialogVisible: (Boolean) -> Unit) {
    val firstDayOfMonth = getFirstDayOfWeek(year, month)
    val daysInMonth = getDaysInMonth(month, year)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            WeekdaysHeader()

            Spacer(modifier = Modifier.height(12.dp))

            // Calculating the number of rows needed for the month view
            val numRows = (firstDayOfMonth + daysInMonth - 1) / 7 + 1

            for (row in 0 until numRows) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (col in 0 until 7) {
                        val dayOfMonth = row * 7 + col - firstDayOfMonth + 1
                        if (dayOfMonth in 1..daysInMonth) {
                            CalendarItem(dayOfMonth, setTaskDialogVisible)
                        } else {
                            Spacer(modifier = Modifier.width(40.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun WeekdaysHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val weekdays = WeekDays.values()
        for (weekday in weekdays) {
            Text(
                text = weekday.name,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
}
@Composable
fun CalendarItem(day: Int, setTaskDialogVisible: (Boolean) -> Unit) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = backgroundPrimary,
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    setTaskDialogVisible(true)
                }
        ) {
            Text(
                text = day.toString(),
            )
        }
    }
}