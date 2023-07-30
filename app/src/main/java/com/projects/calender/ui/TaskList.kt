package com.projects.calender.ui

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.projects.calender.R
import com.projects.calender.entities.Response
import com.projects.calender.viewmodels.CalenderViewModel

@Composable
fun TaskList(
    calendarViewModel: CalenderViewModel,
    context: Context
) {
    Box {
        if (calendarViewModel.isNetworkAvailable(context = context)) {
            when (val taskListState = calendarViewModel.taskListState.value) {
                is Response.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    )
                }

                is Response.Success -> {
                    LazyColumn(
                        modifier = Modifier.padding(
                            top = 12.dp,
                            start = 12.dp,
                            end = 12.dp
                        )
                    ) {
                        taskListState.data?.let {
                            items(it.tasks) { task ->
                                TaskItem(task, calendarViewModel)
                            }
                        }

                    }
                }

                is Response.Failure -> {
                   Text(text = stringResource(R.string.something_went_wrong))
                }
            }
        } else {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.please_check_your_internet_connection)
            )

        }
    }
}