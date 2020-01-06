package com.example.myplumbmen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myplumbmen.R
import kotlinx.android.synthetic.main.activity_select_lvl.*

class SelectLvL : AppCompatActivity() {

    private var lvlSelected = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_lvl)


        selectBtn1.setOnClickListener {
            startLvL(selectBtn1.text.toString().toInt())
        }
        selectBtn2.setOnClickListener {
            startLvL(selectBtn2.text.toString().toInt())
        }
        selectBtn3.setOnClickListener {
            startLvL(selectBtn3.text.toString().toInt())
        }
        selectBtn4.setOnClickListener {
            startLvL(selectBtn4.text.toString().toInt())
        }
        selectBtn5.setOnClickListener {
            startLvL(selectBtn5.text.toString().toInt())
        }


    }
    private fun startLvL(lvlNum:Int){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("lvl", lvlNum)
        startActivity(intent)
    }
}
