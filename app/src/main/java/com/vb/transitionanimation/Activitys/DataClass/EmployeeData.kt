package com.vb.transitionanimation.Activitys.DataClass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "employee")
data class EmployeeData(@PrimaryKey(autoGenerate = true)
                        var id:Long,var fullName:String,var userName:String,var passWord:String,
                 var mobileNo:String,var addRess:String,var City:String,var State:String,@Embedded val experience: Experience?)


data class Experience(var experience1:String,
                      var experience2:String,
                      var designation1:String,
                      var designation2:String
)
