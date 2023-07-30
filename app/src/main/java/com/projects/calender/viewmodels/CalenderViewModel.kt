package com.projects.calender.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.calender.entities.Response
import com.projects.calender.entities.TaskResponseModel
import com.projects.calender.entities.UserId
import com.projects.calender.entities.UserTaskIdModel
import com.projects.calender.entities.UserTaskModel
import com.projects.calender.networking.RetrofitService
import com.projects.calender.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

const val USER_ID = 654321
class CalenderViewModel(private val taskRepository: TaskRepository): ViewModel() {


    private val _taskListState = mutableStateOf<Response<TaskResponseModel>>(Response.Success(null))
    val taskListState: State<Response<TaskResponseModel>> = _taskListState

    init {
        getCalendarTaskLists(UserId(USER_ID))
    }

    fun storeCalendarTask(userTaskModel: UserTaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskRepository.storeCalendarTask(userTaskModel.copy(userId = USER_ID))
                getCalendarTaskLists(UserId(USER_ID))
            } catch (e: Exception) {
                throw IOException(e)
            }
        }
    }

    fun deleteCalendarTask(userTaskIdModel: UserTaskIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskRepository.deleteCalendarTask(userTaskIdModel)
                getCalendarTaskLists(UserId(USER_ID))
            } catch (e: Exception) {
                throw IOException(e)
            }
        }
    }

    private fun getCalendarTaskLists(userId: UserId) {
        viewModelScope.launch {
            taskRepository.getCalendarTaskLists(userId).collect{
                _taskListState.value = it
            }
        }
    }
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
            if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting) {
                return true
            }
        }
        return false
    }

}