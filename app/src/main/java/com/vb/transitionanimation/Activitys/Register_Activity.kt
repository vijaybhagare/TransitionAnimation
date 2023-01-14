package com.vb.transitionanimation.Activitys

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.sign_in_activity.*

class Register_Activity : AppCompatActivity() {
    var validString:String="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    lateinit var sharedPreferences1: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        sharedPreferences1=getSharedPreferences("REGISTERDATA", Context.MODE_PRIVATE)

        register_intent_call()
        register_animation()
    }

    private fun register_animation() {

        val lefttoright= AnimationUtils.loadAnimation(this, R.anim.lefttoright)
        tv_create_an_account.startAnimation(lefttoright)
        img_powl2.startAnimation(lefttoright)


        val righttoleft= AnimationUtils.loadAnimation(this, R.anim.righttoleft)
        register_TextInputLayout1.startAnimation(righttoleft)
        register_TextInputLayout2.startAnimation(righttoleft)
        register_TextInputLayout3.startAnimation(righttoleft)
        register_TextInputLayout4.startAnimation(righttoleft)

        val bottomtotop= AnimationUtils.loadAnimation(this, R.anim.bottomtotop)
        btn_register_in.startAnimation(bottomtotop)
        register_LinearLayout1.startAnimation(bottomtotop)
        register_LinearLayout2.startAnimation(bottomtotop)
        register_LinearLayout3.startAnimation(bottomtotop)

    }

    private fun register_intent_call() {
        tv_back_to_sign_in.setOnClickListener {
            val intent=Intent(this,Sign_in_Activity::class.java)
            overridePendingTransition(R.anim.righttoleft,R.anim.lefttoright);
            startActivity(intent)
        }

        btn_register_in.setOnClickListener {

            validation()

        }

    }
    fun validation(){

        val fullname=et_fullname_registeractivity.text.toString()
        val rusername=et_username_registeractivity.text.toString()
        val rpassword=et_password_registeractivity.text.toString()
        val confpassword=et_conf_password_registeractivity.text.toString()

        if (fullname.isNotEmpty()&&rusername.isNotEmpty()&&rusername.matches(Regex(validString))&&rpassword.isNotEmpty()&&confpassword.equals(rpassword)){

            val editor: SharedPreferences.Editor = sharedPreferences1.edit()
            editor.putString("FULLNAME",fullname)
            editor.putString("USERNAME",rusername)
            editor.putString("PASSWORD",rpassword)
            editor.apply()

            Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this,Create_profile::class.java))
            finish()
        }else{
            Toast.makeText(this, "failed to register", Toast.LENGTH_SHORT).show()
        }
    }
}

