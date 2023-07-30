package com.projects.calender.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.calender.R
import com.projects.calender.entities.TaskModel
import com.projects.calender.entities.UserTaskModel
import com.projects.calender.viewmodels.CalenderViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TaskDialog(
    setTaskDialogVisible: (Boolean) -> Unit,
    calendarViewModel: CalenderViewModel
) {
    var taskTitle by remember {
        mutableStateOf("")
    }
    var taskDesc by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    AlertDialog(onDismissRequest = {
        setTaskDialogVisible(false)
    },
        title = { Text(text = stringResource(R.string.add_task)) },
        text = {
            Column {
                OutlinedTextField(value = taskTitle, onValueChange = { taskTitle = it },
                    shape = RoundedCornerShape(12.dp),
                    label = {
                        Text(stringResource(R.string.title))
                    }

                )
                OutlinedTextField(value = taskDesc, onValueChange = { taskDesc = it },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(128.dp),
                    label = {
                        Text(stringResource(R.string.des_cription))
                    }
                )
            }
        },
        confirmButton = {

            OutlinedButton(
                modifier = Modifier.padding(end = 20.dp),
                onClick = {
                    if (calendarViewModel.isNetworkAvailable(context = context)) {
                        if (taskTitle.isEmpty().not() || taskDesc.isEmpty().not()) {
                            calendarViewModel.storeCalendarTask(
                                UserTaskModel(
                                    userId = 0, taskModel = TaskModel(
                                        title = taskTitle,
                                        description = taskDesc
                                    )
                                )
                            )
                        } else {
                            Toast.makeText(
                                context, context.getText(R.string.please_fill_at_least_one_field),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            context.getText(R.string.please_check_your_internet_connection),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setTaskDialogVisible(false)
                },
                shape = CircleShape,
                border = BorderStroke(1.dp, color = Color.Blue),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
            ) {

                Text(
                    text = stringResource(R.string.add),
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

            }
        },
        dismissButton = {
            OutlinedButton(
                modifier = Modifier.padding(end = 20.dp),
                onClick = {
                    setTaskDialogVisible(false)
                },
                shape = CircleShape,
                border = BorderStroke(1.dp, color = Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
            ) {

                Text(
                    text = stringResource(R.string.cancel),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

            }
        }
    )
}
