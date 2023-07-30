package com.projects.calender.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.calender.R
import com.projects.calender.entities.TaskIdDetailModel
import com.projects.calender.entities.TaskModel
import com.projects.calender.entities.UserTaskIdModel
import com.projects.calender.entities.UserTaskModel
import com.projects.calender.ui.theme.backgroundSecondary
import com.projects.calender.ui.theme.borderPrimary
import com.projects.calender.ui.theme.textPrimary
import com.projects.calender.ui.theme.textTertiary
import com.projects.calender.viewmodels.CalenderViewModel
import com.projects.calender.viewmodels.USER_ID

@Composable
fun TaskItem(task: TaskIdDetailModel, calendarViewModel: CalenderViewModel) {
    val context = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .background(backgroundSecondary, RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = borderPrimary, RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = task.taskModel.title,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = textPrimary,
                    fontWeight = FontWeight.Bold
                ),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                letterSpacing = 0.sp,
                maxLines = 1,
            )
            Text(
                text = task.taskModel.description,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = textTertiary,
                letterSpacing = 0.sp
            )


        }
        Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription = stringResource(
                    R.string.delete),
            modifier = Modifier.clickable {
                if(calendarViewModel.isNetworkAvailable(context = context)) {
                    calendarViewModel.deleteCalendarTask(
                        UserTaskIdModel(
                            userId = USER_ID,
                            taskId = task.taskId
                        )
                    )
                }else{
                    Toast.makeText(context,context.getText(R.string.please_check_your_internet_connection),
                        Toast.LENGTH_SHORT).show()
                }

            }
        )
    }
}