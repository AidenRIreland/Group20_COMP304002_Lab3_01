package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.schedule
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "stop_name") val stopName: String,
    @ColumnInfo(name = "arrival_time") val arrivalTime: Long,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "terminal") val terminal: String
)
