package com.vb.transitionanimation.Activitys

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.animation.AnimationUtils
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.DataClass.Experience
import com.vb.transitionanimation.Activitys.Database.EmpDatabase_Impl
import com.vb.transitionanimation.Activitys.Interface.empDao_Impl
import com.vb.transitionanimation.Activitys.Repository.Repository
import com.vb.transitionanimation.Activitys.ViewModel.MainViewModel
import com.vb.transitionanimation.Activitys.ViewModel.ViewModelFactory
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.activity_selection.*

class Selection : AppCompatActivity() {
    lateinit var sharedPreferences1: SharedPreferences
    lateinit var mainModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        sharedPreferences1=getSharedPreferences("REGISTERDATA",Context.MODE_PRIVATE)
        mainModel=ViewModelProvider(this, ViewModelFactory(Repository())).get(MainViewModel::class.java)
        onclick()
        setAnimation()
    }
    fun  setAnimation(){
        val topbottom=android.view.animation.AnimationUtils.loadAnimation(this,R.anim.topbottom)
        card_selection_activity.startAnimation(topbottom)
    }
    private fun onclick() {
        btn_submit_activity_selection.setOnClickListener {
            get_selection_data()
            savedata_into_roomdatabase( )
            val intent=Intent(this,Sign_in_Activity::class.java)
            overridePendingTransition(R.anim.righttoleft,R.anim.lefttoright)
            startActivity(intent)
            Toast.makeText(this, "Profile created", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun get_selection_data(){
       val Designation1=designation1.text.toString().trim()
       val Designation2=designation2.text.toString().trim()
       val Experience1=experience1.text.toString().trim()
       val Experience2=experience2.text.toString().trim()

        val editor:SharedPreferences.Editor=sharedPreferences1.edit()
        editor.putString("DESIGNATION1",Designation1)
        editor.putString("DESIGNATION2",Designation2)
        editor.putString("DESIGNATION1",Experience1)
        editor.putString("DESIGNATION2",Experience2)
        editor.apply()

    }

    fun savedata_into_roomdatabase(){

        val fullname=sharedPreferences1.getString("FULLNAME","")as String
        val username=sharedPreferences1.getString("USERNAME","")as String
        val password=sharedPreferences1.getString("PASSWORD","")as String
        val mobile=sharedPreferences1.getString("MOBILE","")as String
        val address=sharedPreferences1.getString("ADDRESS","")as String
        val city=sharedPreferences1.getString("CITY","")as String
        val state=sharedPreferences1.getString("STATE","")as String

        val Experience1=sharedPreferences1.getString("STATE","")as String
        val Experience2=sharedPreferences1.getString("STATE","")as String
        val Designation1=sharedPreferences1.getString("STATE","")as String
        val Designation2=sharedPreferences1.getString("STATE","")as String

        mainModel.insert(this, EmployeeData(0,fullname,username,password,mobile,address,city,state,
            Experience(Experience1,Experience2,Designation1,Designation2)
        ))
        Toast.makeText(this, "Profile Create Successful", Toast.LENGTH_SHORT).show()

    }
}