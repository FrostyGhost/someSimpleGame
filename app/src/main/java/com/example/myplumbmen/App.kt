package com.example.myplumbmen

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.*
import androidx.room.Room

class App : Application(), LifecycleObserver {




    var activityInForeground = false


//    companion object {
//        private lateinit var instance : App
//        fun getInstance() : App {
//            return instance
//        }
//    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)



    }
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        activityInForeground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onAppCreated() {
        activityInForeground = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppStarted() {
        activityInForeground = true
    }
}