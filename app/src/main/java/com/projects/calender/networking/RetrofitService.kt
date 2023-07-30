package com.projects.calender.networking

import com.projects.calender.entities.TaskResponseModel
import com.projects.calender.entities.UserId
import com.projects.calender.entities.UserTaskIdModel
import com.projects.calender.entities.UserTaskModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val BASE_URL = "http://dev.frndapp.in:8080"

interface RetrofitService {
    @POST("/api/storeCalendarTask")
    suspend fun storeCalendarTask(@Body userTaskModel: UserTaskModel)

    @POST("/api/deleteCalendarTask")
    suspend fun deleteCalendarTask(@Body userTaskIdModel: UserTaskIdModel)

    @POST("/api/getCalendarTaskList")
    suspend fun getCalendarTaskLists(@Body userId: UserId): TaskResponseModel

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}