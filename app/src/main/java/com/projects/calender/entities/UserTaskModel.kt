package com.projects.calender.entities

import com.google.gson.annotations.SerializedName

data class UserTaskModel(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("task")
    val taskModel: TaskModel
)

data class TaskModel(
    val title: String,
    val description: String
)
data class UserTaskIdModel(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("task_id")
    val taskId: Int
)
data class UserId(
    @SerializedName("user_id")
    val userId: Int
)
data class TaskResponseModel(
    @SerializedName("tasks")
    val tasks: List<TaskIdDetailModel>
)
data class TaskIdDetailModel(
    @SerializedName("task_id")
    val taskId: Int,
    @SerializedName("task_detail")
    val taskModel: TaskModel
)