package com.example.testczydziala

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class StartGame : AppCompatActivity() {
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button: Button
    lateinit var text: TextView
    lateinit var getFrameTime: EditText
    var frameTime = 0f
    var frameTime2 = 0f
    var string = ""
    var help = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        getFrameTime = findViewById(R.id.editText)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button = findViewById(R.id.button)
        text = findViewById(R.id.textView13)
        val text_toast = "Ustaw predkosc!"
        val text_toast2 = "Wpisz wartosci!"

        val duration = Toast.LENGTH_SHORT
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        val toast = Toast.makeText(applicationContext, text_toast, duration)
        val toast2 = Toast.makeText(applicationContext, text_toast2, duration)

        frameTime2=intent.getFloatExtra("frameTime",0f)
        if(frameTime2 != 0f)
            {string = frameTime2.toString()
            text.text = "Ustawiona predkosc: ${string}"
            frameTime = string.toFloat()
                help =1
            }


        //frameTime = string.toFloat()
        button3.setOnClickListener{
            if(help == 1)
            {val intent = Intent (this, FirstLevel::class.java)
                intent.putExtra("frameTime",frameTime)
                startActivity(intent)} else {toast.show()}

        }

        button4.setOnClickListener{
            if(help == 1)
            {val intent = Intent (this,TestMove::class.java)
                intent.putExtra("frameTime",frameTime)
                startActivity(intent)} else {toast.show()}

        }

        button.setOnClickListener{
            if(getFrameTime.text.toString() != "") {
                string = getFrameTime.text.toString()
                text.text = "Ustawiona predkosc: ${string}"
                frameTime = string.toFloat()
                help = 1
            }else {toast2.show()}
        }

    }
}