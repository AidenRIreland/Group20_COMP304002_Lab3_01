package com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.viewmodels

import android.app.Application
import  com.example.group20_comp304002_lab3_01.viewmodels.ui.airlineschedule.database.AppDatabase
class AirlineScheduleApplication : Application(){
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}