package com.example.testczydziala

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class Lose : AppCompatActivity() {
    lateinit var button: Button
    var frameTime = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lose)
        frameTime=intent.getFloatExtra("frameTime",0.5f)
        button = findViewById(R.id.button2)
        button.setOnClickListener{
            val intent = Intent (this,StartGame::class.java)
            intent.putExtra("frameTime",frameTime)
            startActivity(intent)
        }

        }
    }


