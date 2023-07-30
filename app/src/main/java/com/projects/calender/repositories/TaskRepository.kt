package com.projects.calender.repositories

import android.util.Log
import com.projects.calender.entities.Response
import com.projects.calender.entities.TaskResponseModel
import com.projects.calender.entities.UserId
import com.projects.calender.entities.UserTaskIdModel
import com.projects.calender.entities.UserTaskModel
import com.projects.calender.networking.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TaskRepository(private val retrofitService: RetrofitService) {
    suspend fun storeCalendarTask(userTaskModel: UserTaskModel) {
        retrofitService.storeCalendarTask(userTaskModel)
    }

    suspend fun deleteCalendarTask(userTaskIdModel: UserTaskIdModel) {
        retrofitService.deleteCalendarTask(userTaskIdModel)
    }

    suspend fun getCalendarTaskLists(userId: UserId): Flow<Response<TaskResponseModel>> =
        flow {
            try {
                emit(Response.Loading)
                val responseApi = retrofitService.getCalendarTaskLists(userId)
                emit(Response.Success(responseApi))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}