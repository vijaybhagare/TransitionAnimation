package com.vb.transitionanimation.Activitys

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
 import android.graphics.Bitmap
  import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
 import android.widget.Toast
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.activity_create_profile.*

class Create_profile : AppCompatActivity() {
    lateinit var sharedPreferences1: SharedPreferences
        val pic_id=23727
     lateinit var imageurl:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)


        sharedPreferences1=getSharedPreferences("REGISTERDATA",Context.MODE_PRIVATE)
        setAnimation()
        gotoActivity()
        set_data_to_create_profile()
        img_take_photo.setOnClickListener { takephoto()  }

    }
    private fun setAnimation() {
        val bottomup=android.view.animation.AnimationUtils.loadAnimation(this,R.anim.topbottom)
        card_profile.startAnimation(bottomup)
    }

    fun  gotoActivity(){
        btn_continue_create_profile.setOnClickListener {
            savedata_into_sharepreference()
            if (et_profile_activity_fullname.text!!.isNotEmpty()&&et_profile_activity_state.text!!.isNotEmpty()) {
                val intent = Intent(this, Selection::class.java)
                overridePendingTransition(R.anim.righttoleft, R.anim.lefttoright);
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Fill all Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun takephoto() {
        val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val bundle=Bundle()
        bundle.putInt("key", pic_id)
        startActivityForResult(camera_intent, pic_id)
    }
    override fun onActivityResult(requestCode: Int, resultCode : Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        val img:Bitmap= data?.extras?.get("data") as Bitmap


        if (data!=null){

            img_take_photo.setImageBitmap(img)

            imageurl=img
        }else{

            imageurl= R.drawable.men1 as Bitmap
            Toast.makeText(this, "Select image", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun set_data_to_create_profile(){
        val fullname_from_register=sharedPreferences1.getString("FULLNAME","")
        val username_from_register=sharedPreferences1.getString("USERNAME","")
        val password_from_register=sharedPreferences1.getString("PASSWORD","")

          et_profile_activity_fullname.setText(fullname_from_register)
          et_profile_activity_username.setText(username_from_register)

    }
    fun savedata_into_sharepreference(){
        val name=et_profile_activity_fullname.text.toString().trim()
        val username=et_profile_activity_username.text.toString().trim()
        val mobile=et_profile_activity_mobile_no.text.toString().trim()
        val address= et_profile_activity_address.text.toString().trim()
        val city =et_profile_activity_city.text.toString().trim()
        val state=et_profile_activity_state.text.toString().trim()

        val editor:SharedPreferences.Editor=sharedPreferences1.edit()
        editor.putString("FULLNAME",name)
        editor.putString("USERNAME",username)
        editor.putString("MOBILE",mobile)
        editor.putString("ADDRESS",address)
        editor.putString("CITY",city)
        editor.putString("STATE",state)
        editor.putString("IMAGEURL",imageurl.toString())
        editor.apply()
    }
}