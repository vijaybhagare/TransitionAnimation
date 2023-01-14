package com.vb.transitionanimation.Activitys.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.RecyclerView
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.DataClass.Experience
import com.vb.transitionanimation.Activitys.Repository.Repository
import com.vb.transitionanimation.Activitys.Sign_in_Activity
import com.vb.transitionanimation.Activitys.UpdateActivity
import com.vb.transitionanimation.Activitys.ViewModel.MainViewModel
import com.vb.transitionanimation.Activitys.ViewModel.ViewModelFactory
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.dialog_item.view.*
import kotlinx.android.synthetic.main.item_recycle.view.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MyAdapter( val context: Context, var emplist:ArrayList<EmployeeData>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    lateinit var mainview: MainViewModel

    var eid: Long = 0

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val Fullname: TextView
        val Mobile: TextView
        val City: TextView
        val State: TextView
        val card: CardView
        val deletebtn: ImageButton

        init {
            Fullname = itemview.tv_fullname
            Mobile = itemview.tv_mobile_no
            City = itemview.tv_city
            State = itemview.tv_state
            card = itemview.card_adapter
            deletebtn = itemview.btn_delete_record
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val rootview =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent, false)

        return ViewHolder(rootview)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.Fullname.text = (emplist.get(position).fullName).toString()
        holder.Mobile.text = (emplist.get(position).mobileNo).toString()
        holder.City.text = (emplist.get(position).City).toString()
        holder.State.text = (emplist.get(position).State).toString()


        holder.deletebtn.setOnClickListener {

            deleteonerecord()
        }

        holder.card.setOnClickListener {
            eid = (emplist.get(position).id).toLong()

            val intent = Intent(context, UpdateActivity::class.java)

            val bundle = Bundle()

            bundle.putString("ID", emplist.get(position).id.toString())
            bundle.putString("A1", emplist.get(position).fullName)
            bundle.putString("A2", emplist.get(position).mobileNo)
            bundle.putString("A3", emplist.get(position).City)
            bundle.putString("A4", emplist.get(position).State)
            intent.putExtras(bundle)
            context.startActivity(intent)

        }

    }

    fun showdata(elist: ArrayList<EmployeeData>) {
        this.emplist = elist
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return emplist.size
    }


    fun deleteonerecord() {

        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle("Delete Alert")
        //set message for alert dialog
        builder.setMessage("Do you want to delete")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->


            mainview = ViewModelProvider(ViewModelStore(), ViewModelFactory(Repository())).get(
                MainViewModel::class.java
            )
            mainview.deleteone(context, eid)

        }
        //performing cancel action
        builder.setNeutralButton("Cancel") { dialogInterface, which ->

        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->

        }

        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }


}