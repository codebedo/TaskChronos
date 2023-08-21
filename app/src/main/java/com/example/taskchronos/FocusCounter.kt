package com.example.taskchronos


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception


class FocusCounter : AppCompatActivity() {

    private lateinit var counterTextView: TextView
    private lateinit var startButton: Button
    private val handler = Handler()
    private var isCountingDown = true
    private var remainingTimeInMillis = 120 * 60 * 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_counter)

        counterTextView = findViewById(R.id.counter_text_view)
        startButton = findViewById(R.id.start_button)
        updateCounterText()
    }

    private val countRunnable = object : Runnable {
        override fun run() {
            if (isCountingDown) {
                remainingTimeInMillis -= 1000
                if (remainingTimeInMillis <= 0) {
                    remainingTimeInMillis = 0
                    stopCounter()
                }
                updateCounterText()
            } else {
                remainingTimeInMillis += 1000
                if (remainingTimeInMillis >= 120 * 60 * 1000) {
                    remainingTimeInMillis = 120 * 60 * 1000
                }
                updateCounterText()
            }
            handler.postDelayed(this, 1000)
        }
    }

    fun toggleCounter(view: View) {
        isCountingDown = !isCountingDown
        startButton.text = if (isCountingDown) "Pause" else "Play"
        if (isCountingDown) {
            startCounter()
        } else {
            stopCounter()
        }
    }

    private fun startCounter() {
        handler.removeCallbacks(countRunnable)
        handler.postDelayed(countRunnable, 1000)
    }

    private fun stopCounter() {
        handler.removeCallbacks(countRunnable)
    }

    // ... Diğer işlevler burada yer alır
/*
    private fun updateCounterText() {
        val minutes = (remainingTimeInMillis / (1000 * 60)).toInt()
        val seconds = (remainingTimeInMillis / 1000) % 60
        val timeText = String.format("%02d:%02d", minutes, seconds)
        counterTextView.text = timeText
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCounter()
    }
    */

}

