package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.schedule.Schedule
import com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.schedule.ScheduleDao

@Database(entities = [Schedule::class], version = 3) // Increment the version number here
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("airline_schedule.db")
                    //.fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}