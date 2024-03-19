package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.schedule
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert
    fun insert(schedule: Schedule)

    @Query("SELECT * FROM schedule")
    fun getAllSchedules(): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getByStopName(stopName: String): Flow<List<Schedule>>  // Flow<Int>
}