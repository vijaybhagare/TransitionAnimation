package com.vb.transitionanimation.Activitys.Fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vb.transitionanimation.Activitys.Adapter.MyAdapter
import com.vb.transitionanimation.Activitys.DataClass.EmployeeData
import com.vb.transitionanimation.Activitys.Database.EmpDatabase_Impl
 import com.vb.transitionanimation.Activitys.Repository.Repository
import com.vb.transitionanimation.Activitys.Sign_in_Activity
import com.vb.transitionanimation.Activitys.UpdateActivity
import com.vb.transitionanimation.Activitys.ViewModel.MainViewModel
import com.vb.transitionanimation.Activitys.ViewModel.ViewModelFactory
import com.vb.transitionanimation.R
import kotlinx.android.synthetic.main.dialog_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment(): Fragment() {

    lateinit var mainModel: MainViewModel
    lateinit var myAdapter: MyAdapter
    lateinit var rootview:View
      @SuppressLint("SuspiciousIndentation")
      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootview= inflater.inflate(R.layout.fragment_home, container, false)
          mainModel= ViewModelProvider(this,ViewModelFactory(Repository())).get(MainViewModel::class.java)
      myAdapter= MyAdapter( requireContext(),  ArrayList<EmployeeData>())
          rootview.re_cycle.apply {

            layoutManager=LinearLayoutManager(context)
             adapter=myAdapter

          }


         return rootview

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        rootview= re_cycle.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = myAdapter
            getdata()
        }
    }


    fun getdata(){



        mainModel.getalldata(requireContext())?.observe(requireActivity(), Observer {

            myAdapter.showdata(it as ArrayList<EmployeeData>)

            })
        }



    }







