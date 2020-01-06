package com.example.myplumbmen.activity

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.myplumbmen.R
import com.example.myplumbmen.R.drawable.ic_bottom_road
import com.example.myplumbmen.data.LvLStructure
import com.example.myplumbmen.util.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myplumbmen.R.drawable.ic_top_road as ic_top_road

class MainActivity : AppCompatActivity() {

    var x:Int = 720
    var y:Int = 1080
    var h: Int = 100
    private var matrixSizeH = 5
    private var matrixSizeW = 5
    private lateinit var containerArray:Array<LinearLayout>
    private var btnIdArray:ArrayList<Int> = ArrayList()
//    private lateinit var emptyArray:ArrayList<Int> = ArrayList()
    private lateinit var emptyArray:ArrayList<Int>
    private lateinit var currentLvl: LvLStructure
    var startId = 0
    lateinit var currentAnswerArray: IntArray
    var q = 0
    var LvlNum =0


    private var bg  = arrayOf(
        R.drawable.ic_top,
        ic_bottom_road,
        R.drawable.ic_road3,
        R.drawable.ic_road4,
        R.drawable.ic_round,
        R.drawable.ic_tonel_1,
        R.drawable.ic_tonel_2,
        R.drawable.ic_tonel_3,
        R.drawable.ic_tonel_4,
        R.drawable.ic_border

    )
    override fun onStart() {
        LvlNum = intent.getIntExtra("lvl", 0)
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        x = size.x
        y = size.y
        h = x / (matrixSizeW+1)

        val jsonParser = JsonParser()
        textView2.setText(R.string.lvl3)
         currentLvl = jsonParser.getLvl(textView2.text.toString())

        containerArray = arrayOf(containetLine1,containetLine2,containetLine3,containetLine4,containetLine5,
            containetLine6,containetLine7,containetLine8,containetLine9,containetLine10,containetLine11)

        currentAnswerArray = IntArray((currentLvl.matrixX!! * currentLvl.matrixY!!)-2)


        emptyArray = ArrayList()
        btnMatrixSize.setOnClickListener {
//                initMatrix(matrixSizeH,matrixSizeW)
                initMatrix(currentLvl.matrixX!!, currentLvl.matrixY!!)
                btnMatrixSize.isClickable = false
//                currentAnswerArray = IntArray((currentLvl.matrixX!! * currentLvl.matrixY!!)-2)
                initAnswerArray()
        }




    }



    private fun initAnswerArray(){
        for (x in 0 until currentAnswerArray.size){
            if (currentLvl.endArray!![x]==currentLvl.startArray!![x]){
                emptyArray[x] = 1
            }
        }
    }

    private fun initMatrix(sizeH: Int, sizeW: Int){

        for (x in 0 until sizeW){
            textView.append("$x \n" )
            for (y in 0 until sizeH){
                val btn = createBtn(y)
                containerArray[x].addView(btn)
                textView.append("${btn.id} \t")
            }
        }
    }

    private fun createBtn(int: Int): Button {
        val btn  = Button(this)
        btn.layoutParams = LinearLayout.LayoutParams(h,h)
        btn.id = startId
        btn.setBackgroundResource(bg[currentLvl.backgroundArray!![startId]])
        btn.text = currentLvl.startArray!![startId].toString()
//        btn.text = startId.toString()
        btn.rotation = btn.rotation + (90f*currentLvl.startArray!![startId])
        btn.setTextColor(Color.parseColor("#22000000"))
        btn.setOnClickListener(View.OnClickListener { click(it as Button) })

        btnIdArray.add(btn.id)
        startId++

        emptyArray.add(0)
//        currentAnswerArray.add(currentLvl.startArray!![startId])
        return btn
    }

   private fun click (view: Button): View.OnClickListener? {
       view.rotation = view.rotation + 90f
       //Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
       when (currentLvl.backgroundArray!![view.id]){
           0 ->
               if (view.text.toString().toInt() != 7){
                    if (view.text.toString().toInt() == 0){
                   view.text = "1"
                    }else { view.text = "0" }


                   if (view.text.toString().toInt() == currentLvl.answerArray!![view.id]){
                       emptyArray[view.id] = 1
                   }else {
                       emptyArray[view.id] = 0
                   }
                   check()
               }
           1 ->
               if (view.text.toString().toInt() != 7){
                    if (view.text.toString().toInt() == 0){
                        view.text = "1"
                    }else { view.text = "0" }


                   if (view.text.toString().toInt() == currentLvl.answerArray!![view.id]){
                       emptyArray[view.id] = 1
                   }else {
                       emptyArray[view.id] = 0
                   }
                   check()
               }
           2 ->
               if (view.text.toString().toInt() != 7){
                    if (view.text.toString().toInt() <=2){
                        val num = view.text.toString().toInt()+1
                        view.text = num.toString()
                        //Toast.makeText(this,view.text.toString(),Toast.LENGTH_LONG).show()
                    }else { view.text = "0" }


                   if (view.text.toString().toInt() == currentLvl.answerArray!![view.id]){
                       emptyArray[view.id] = 1
                   }else {
                       emptyArray[view.id] = 0
                   }
                   check()
               }
           3->
               if (view.text.toString().toInt() != 7){
               view.text = currentLvl.answerArray!![view.id].toString()
                   if (view.text.toString().toInt() == currentLvl.answerArray!![view.id]){
                       emptyArray[view.id] = 1
                   }else {
                       emptyArray[view.id] = 0
                   }
                   check()
               }
           4->
               if (view.text.toString().toInt() != 7){
                    if (view.text.toString().toInt() >=3){
                         view.setText(view.text.toString().toInt()+1)
                        //Toast.makeText(this,view.text.toString(),Toast.LENGTH_LONG).show()
                    }else { view.text = "0" }


                   if (view.text.toString().toInt() == currentLvl.answerArray!![view.id]){
                       emptyArray[view.id] = 1
                   }else {
                       emptyArray[view.id] = 0
                   }
                   check()
               }
       }

       return null
   }

    fun check(){
        q = 0
       for(x in 0 until currentLvl.endArray!!.size){

//           if (x != currentLvl.endArray!!.size-1){
               if (emptyArray[x]==currentLvl.endArray!![x]){
                   Toast.makeText(this,x.toString(),Toast.LENGTH_LONG).show()
                   q++
               }
               Toast.makeText(this,q.toString(),Toast.LENGTH_LONG).show()

           if (q == currentLvl.endArray!!.size){
               Toast.makeText(this,"GAME OVER",Toast.LENGTH_LONG).show()
           }
           else{
               Toast.makeText(this,"No",Toast.LENGTH_LONG).show()
           }
           }

       }

    }



