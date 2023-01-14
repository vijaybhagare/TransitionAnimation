package com.vb.transitionanimation.Activitys.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vb.transitionanimation.Activitys.Repository.Repository

class ViewModelFactory(private val repositoryCountry:Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel( this.repositoryCountry) as T
        }else
        {
            throw java.lang.IllegalArgumentException("View model not found")
        }
    }
}