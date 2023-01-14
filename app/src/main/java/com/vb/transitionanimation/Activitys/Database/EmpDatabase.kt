package com.vb.transitionanimation.Activitys.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.Interface.empDao


@Database(entities = [EmployeeData::class], version = 2, exportSchema = false)
abstract class EmpDatabase: RoomDatabase(){

    abstract fun getDao():empDao

    companion object{
        private var DATABASE_NAME="EMPLOYEEDATABASE"

        @Volatile
        var instance:EmpDatabase?=null
        fun getInstance(context: Context):EmpDatabase?{

            if (instance==null) {

                synchronized(EmpDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context, EmpDatabase::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance
        }
    }
}