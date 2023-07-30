package com.projects.calender.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projects.calender.entities.Months
import com.projects.calender.ui.theme.backgroundSecondary

@Composable
fun YearMonthPicker(selectedYear: Int, selectedMonth: Int, onDateSelected: (Int, Int) -> Unit) {
    var visible by remember {
        mutableStateOf(false)
    }
    MonthPicker(
        visible = visible,
        currentMonth = selectedMonth,
        currentYear = selectedYear,
        confirmButtonCLicked = { month_, year_ ->
            onDateSelected(year_, month_)
            visible = false
        },
        cancelClicked = {
            visible = false
        }
    )
    OutlinedButton(
        onClick = { visible = true },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = backgroundSecondary),

        ) {
        Text("${Months.values()[selectedMonth]} , $selectedYear")
    }
}