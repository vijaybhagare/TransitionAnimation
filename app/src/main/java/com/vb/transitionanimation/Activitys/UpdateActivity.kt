package com.vb.transitionanimation.Activitys

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.Repository.Repository
import com.vb.transitionanimation.Activitys.ViewModel.MainViewModel
import com.vb.transitionanimation.Activitys.ViewModel.ViewModelFactory
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.dialog_item.*
import kotlinx.android.synthetic.main.dialog_item.view.*

class UpdateActivity:AppCompatActivity() {
    lateinit var mainview: MainViewModel

     var fname:String?=null
     var fmobile:String?=null
     var fcity:String?=null
     var fstate:String?=null
      var id:String?=null

    lateinit var fbutton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_item)


        mainview = ViewModelProvider(this, ViewModelFactory(Repository())).get(MainViewModel::class.java)




        val bundle = intent.extras
        id=bundle!!.getString("ID","").toString()
        val s1 = bundle!!.getString("A1","").toString()
        val s2 = bundle!!.getString("A2","").toString()
        val s3 = bundle!!.getString("A3","").toString()
        val s4 = bundle!!.getString("A4","").toString()


        et_update_fullname.setText(s1)
        et_update_mobile.setText(s2)
        et_update_city.setText(s3)
        et_update_state.setText(s4)


        btn_update_button.setOnClickListener {
            updetedata()
        }

    }

    private fun updetedata() {


        val get_name=et_update_fullname.text.toString()
        val get_mobile=et_update_mobile.text.toString()
        val get_city=et_update_city.text.toString()
        val get_state=et_update_state.text.toString()


        mainview.updateall(this, EmployeeData(id!!.toLong(),get_name,"","",get_mobile,"",get_city,get_state,experience = null))

        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

        startActivity(Intent(this,DashboardActivity::class.java))



    }

}