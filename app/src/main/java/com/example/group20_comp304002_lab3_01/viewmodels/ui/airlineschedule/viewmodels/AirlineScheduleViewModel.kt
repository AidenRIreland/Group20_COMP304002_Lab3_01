package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.schedule.Schedule
import com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class AirlineScheduleViewModel (private val scheduleDao: ScheduleDao):ViewModel(){

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAllSchedules()
    fun getByStopName(name: String): Flow<List<Schedule>> = scheduleDao.getByStopName(name)
}
class AirlineScheduleViewModelFactory(private val scheduleDao: ScheduleDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirlineScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirlineScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}