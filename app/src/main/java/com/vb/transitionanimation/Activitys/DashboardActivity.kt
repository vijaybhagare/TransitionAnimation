package com.vb.transitionanimation.Activitys

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.vb.transitionanimation.Activitys.Fragment.GroupFragment
import com.vb.transitionanimation.Activitys.Fragment.HomeFragment
import com.vb.transitionanimation.Activitys.Fragment.MessageFragment
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.header_drawer.*
import java.util.*
import java.net.URL

class DashboardActivity : AppCompatActivity() {
    lateinit var sharedPreferences1: SharedPreferences

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navView:NavigationView
    lateinit var bottombar:BottomNavigationView
     @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
         sharedPreferences1=getSharedPreferences("REGISTERDATA", Context.MODE_PRIVATE)
         drawerLayout = findViewById(R.id.nav_drawer)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

         supportActionBar?.setDisplayHomeAsUpEnabled(true)
         supportActionBar?.title="Dashboard"

        navView = findViewById(R.id.navigation_view)
         bottombar=findViewById(R.id.bottom_navigation_bar)


         replaceFragment(HomeFragment())

        navwmenuselect()
         chengefragment()
         set_data_to_dashboard_header()

    }

    fun  set_data_to_dashboard_header(){
        val fullname=sharedPreferences1.getString("FULLNAME","")
        val mobile_no=sharedPreferences1.getString("MOBILE","")
        val imageurl=sharedPreferences1.getString("IMAGEURL","")

        Toast.makeText(this, "$fullname", Toast.LENGTH_SHORT).show()


        val navigationView : NavigationView  = findViewById(R.id.navigation_view)
        val headerView : View = navigationView.getHeaderView(0)
        val Header_name : TextView = headerView.findViewById(R.id.header_name)
        val Header_mo : TextView = headerView.findViewById(R.id.header_mobile_no)


       // Glide.with(this).load("$imageurl").into(header_profile_image)
         Header_name.text=fullname.toString()
         Header_mo.text=mobile_no.toString()

    }


    private fun navwmenuselect() {

        navView.setNavigationItemSelectedListener { menuItem->

            when(menuItem.itemId){

                R.id.home->{
                    Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
                    true

                }

                R.id.item2->{
                    Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.item3->{
                    Toast.makeText(this, "item3", Toast.LENGTH_SHORT).show()
                    true

                }

                R.id.term_condition->{


                    Toast.makeText(this, "terms condition", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.about_us->{
                    println("About us Clicked")


                    Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.exit->{


                    val builder=AlertDialog.Builder(this)
                    builder.setTitle("Exit Alert")
                    builder.setMessage("Do you want to exit")

                    builder.setPositiveButton("yes"){_,_->
                        val editor:SharedPreferences.Editor=sharedPreferences1.edit()
                        editor.clear()
                        editor.apply()
                        Toast.makeText(this, "Exited", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,Sign_in_Activity::class.java))
                        finish()

                    }

                    builder.setNeutralButton("Cancel"){_,_->

                    }

                    builder.setNegativeButton("NO"){_,_,->

                    }
                    val dialog:AlertDialog=builder.create()
                    dialog.setCancelable(false)
                    dialog.show()

                    true
                }

                else -> {
                    false
                }
            }
        }
    }


    fun chengefragment(){

        bottombar.setOnItemSelectedListener {

            when(it.itemId){
                R.id.self_home->{
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.group->{
                    replaceFragment(GroupFragment())
                    true
                }
                R.id.message->{
                    replaceFragment(MessageFragment())
                    true
                }
                else -> {false}
            }
        }
    }



    fun replaceFragment(fragment: Fragment){

        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framly, fragment)
        fragmentTransaction.commit()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

}



}