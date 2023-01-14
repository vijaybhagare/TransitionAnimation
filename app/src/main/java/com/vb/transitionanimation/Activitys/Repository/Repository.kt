package com.vb.transitionanimation.Activitys.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.Database.EmpDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository() {

 companion object{

  private var database:EmpDatabase?=null

  var context:Context?=null
  var employee:EmployeeData?=null


  fun initializeDB(context: Context):EmpDatabase?{
   return EmpDatabase.getInstance(context)

  }

  fun insertemp(context: Context,employeeData: EmployeeData){

   database= initializeDB(context)
   CoroutineScope(IO).launch {
    database?.getDao()?.insertdata(employeeData)
   }
  }


  fun showemp(context: Context):LiveData<List<EmployeeData>>?{

   database= initializeDB(context)

   return database?.getDao()?.getallemployee()
  }

  fun updateall(context: Context,employeeData: EmployeeData){

   database= initializeDB(context)
   CoroutineScope(IO).launch {
    database?.getDao()?.update(employeeData)
   }

  }

  fun logincredential(context: Context,user:String,pass:String): LiveData<EmployeeData>? {

   database= initializeDB(context)
   return database?.getDao()?.readLoginData(user,pass)

    }

  fun deleteone(context:Context, id:Long) {

   database= initializeDB(context)

   CoroutineScope(IO).launch {
    database?.getDao()?.deleteone(id)
   }


  }


 }

 }