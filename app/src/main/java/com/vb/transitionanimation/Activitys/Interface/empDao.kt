package com.vb.transitionanimation.Activitys.Interface

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData


@Dao
interface empDao {


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertdata(employeeData: EmployeeData)

    @Query("SELECT * FROM employee")
    fun getallemployee():LiveData<List<EmployeeData>>


    @Update
    fun update(employeeData: EmployeeData)


    @Query("SELECT * FROM employee WHERE userName LIKE :user AND passWord LIKE :password")
    fun readLoginData(user: String, password: String):LiveData<EmployeeData>


    @Query("DELETE FROM EMPLOYEE WHERE id=:id")
    fun deleteone(id: Long)

}