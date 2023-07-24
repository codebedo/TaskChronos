package com.example.taskchronos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var counteButton : Button


class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        counteButton = findViewById(R.id.counter_button)

        counteButton.setOnClickListener {
            val intent = Intent(baseContext , FocusCounter :: class.java  )
            startActivity(intent)
        }

    }
}