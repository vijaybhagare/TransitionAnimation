package com.vb.transitionanimation.Activitys

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.DataClass.Experience
import com.vb.transitionanimation.Activitys.Database.EmpDatabase
 import com.vb.transitionanimation.Activitys.Interface.empDao
 import com.vb.transitionanimation.Activitys.Repository.Repository
import com.vb.transitionanimation.Activitys.ViewModel.MainViewModel
import com.vb.transitionanimation.Activitys.ViewModel.ViewModelFactory
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.dialog_item.view.*
import kotlinx.android.synthetic.main.sign_in_activity.*

class Sign_in_Activity : AppCompatActivity() {
    lateinit var sharedPreferences1: SharedPreferences


    lateinit var mainview: MainViewModel
    lateinit var empDatabase: EmpDatabase
    lateinit var empDao: empDao
    var passWord: String? = null
    var isRemembered = false
    var validString: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        supportActionBar?.hide()
        sharedPreferences1 = getSharedPreferences("REGISTERDATA", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences1.getBoolean("CHECKBOX", false)

        empDatabase = Room.databaseBuilder(this, EmpDatabase::class.java, "EMPLOYEEDATABASE")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
        empDao = empDatabase.getDao()

        mainview =
            ViewModelProvider(this, ViewModelFactory(Repository())).get(MainViewModel::class.java)
        if (isRemembered) {

            startActivity(Intent(this, DashboardActivity::class.java))
            finish()

        } else {
            Toast.makeText(this, "not selected", Toast.LENGTH_SHORT).show()
        }

        setanimation()
        gotoActivity()

        et_username_loginpage.setText("vijay@gmail.com")
        et_password_loginpage.setText("Vijay@123")

        btn_sign_in.setOnClickListener {
            validation()
            putcheckbox()
            Authentication()

        }
    }

    fun Authentication() {

        val s1=et_username_loginpage.text.toString()
        val s2=et_password_loginpage.text.toString()

        val user=mainview.LoginCredential(this, s1,s2)

        if (user!=null){

            startActivity(Intent(this,DashboardActivity::class.java))
            finish()

        }


    }

    private fun setanimation() {

        val toptobottom = AnimationUtils.loadAnimation(this, R.anim.topbottom)

        val lefttoright = AnimationUtils.loadAnimation(this, R.anim.lefttoright)
        tv_signin.startAnimation(lefttoright)
        img_powl.startAnimation(lefttoright)

        val righttoleft = AnimationUtils.loadAnimation(this, R.anim.righttoleft)
        filledTextField.startAnimation(righttoleft)
        textfield2.startAnimation(righttoleft)
        check_me.startAnimation(righttoleft)

        val bottomtotop = AnimationUtils.loadAnimation(this, R.anim.bottomtotop)
        btn_sign_in.startAnimation(bottomtotop)
        LinearLayout2.startAnimation(bottomtotop)
        LinearLayout3.startAnimation(bottomtotop)
    }

    fun gotoActivity() {
        tv_go_to_sign_up.setOnClickListener {
            val intent = Intent(this, Register_Activity::class.java)
            overridePendingTransition(R.anim.righttoleft, R.anim.lefttoright);
            startActivity(intent)
        }
    }

    fun validation() {
        val inputText = filledTextField.editText?.text.toString()
        val email = et_username_loginpage.text.toString()
        val pass = et_password_loginpage.text.toString()
        if (email.matches(validString.toRegex()) && pass.isNotEmpty()) {
        } else {
            et_username_loginpage.error = "Invalid Username"
            et_password_loginpage.error = "Enter Password"

        }
    }

    fun putcheckbox() {
        val checked: Boolean = check_me.isChecked
        val editor: SharedPreferences.Editor = sharedPreferences1.edit()
        editor.putBoolean("CHECKBOX", checked)
        editor.apply()
    }




}






