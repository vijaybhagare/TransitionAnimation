package com.vb.transitionanimation.Activitys.ViewModel

import android.content.Context
import androidx.lifecycle.*
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.Repository.Repository

class MainViewModel(repositoryCountry: Repository) : ViewModel() {

      var string=MutableLiveData<EmployeeData?>()

    fun insert(context: Context,dataclass:EmployeeData){

        return Repository.insertemp(context,dataclass)
    }


    fun getalldata(context: Context):LiveData<List<EmployeeData>>?{
        return Repository.showemp(context)
    }



    fun updateall(context: Context,employeeData: EmployeeData){

        return Repository.updateall(context,employeeData)
    }

    fun LoginCredential(context: Context,username:String,password:String): LiveData<EmployeeData>? {

        return Repository.logincredential(context,username,password)
     }
    fun deleteone(context: Context,id:Long){

        return Repository.deleteone(context,id)
    }


}