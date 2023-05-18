package com.example.testczydziala

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Win : AppCompatActivity() {
    lateinit var button2: Button
    var frameTime = 0f;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        frameTime=intent.getFloatExtra("frameTime",0.5f)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener{
            val intent = Intent (this,SecondLevel::class.java)
            intent.putExtra("frameTime",frameTime)
            startActivity(intent)
        }

    }
}