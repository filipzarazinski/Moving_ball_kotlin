package com.example.testczydziala

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Win_final : AppCompatActivity() {
    lateinit var button2: Button
    var frameTime = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_final)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        frameTime=intent.getFloatExtra("frameTime",0.5f)
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener{
            val intent = Intent (this,StartGame::class.java)
            intent.putExtra("frameTime",frameTime)
            startActivity(intent)
        }

    }
}