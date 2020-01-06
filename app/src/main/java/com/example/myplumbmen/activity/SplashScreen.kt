package com.example.myplumbmen.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.myplumbmen.App
import com.example.myplumbmen.R

class SplashScreen : AppCompatActivity(), LifecycleObserver {


    private var firstOpen : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

//        Glide.with(this)
//            .load(R.drawable.download)
//            .into(ivIcon)

        val sp = getPreferences(Context.MODE_PRIVATE).getBoolean("pref_first_launch", true)
        firstOpen = sp



        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        //Handler().postDelayed({
           // if(App.getInstance().activityInForeground) {
                when(firstOpen) {
                    true -> {
                        startActivity(Intent(this,SelectLvL::class.java)) }
                    false -> {
                        startActivity(Intent(this,SelectLvL::class.java)) }
                }
           // }
        //}, splashScreenDuration)
    }

    private fun getSplashScreenDuration(): Long {
        val sp = getPreferences(Context.MODE_PRIVATE)
        val prefKeyFirstLaunch = "pref_first_launch"

        return when(sp.getBoolean(prefKeyFirstLaunch, true)) {
            true -> {
                sp.edit().putBoolean(prefKeyFirstLaunch, false).apply()
                2000 }
            false -> { 2000 }
        }
    }

}

