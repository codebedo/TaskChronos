package com.example.taskchronos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // we creating a thread for splash

        val background = object : Thread(){
            override fun run() {
                try {
                    // this threadd for 3 or 3000 ms go sleep
                    Thread.sleep(3000)
                    // and we using intent for go to the next page
                    val intent = Intent (baseContext, HomePage::class.java)
                    startActivity(intent)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}