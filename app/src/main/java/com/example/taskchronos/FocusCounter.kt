package com.example.taskchronos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView

private lateinit var counterTextView: TextView
private val handler = Handler()
private var isCountingDown = true
private var remainingTimeInMillis = 120 * 60 * 1000


class FocusCounter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_counter)

        counterTextView = findViewById(R.id.counter_text_view)
        updateCounterText()
    }

    private val countDown = Runnable {
        if (isCountingDown) {
            remainingTimeInMillis -= 1000
            if (remainingTimeInMillis <= 0) {
                remainingTimeInMillis = 0
                isCountingDown = false
            }
            updateCounterText()
            handler.postDelayed(this, 1000)
        }
    }

    private val countUp = Runnable {
        if (!isCountingDown) {
            remainingTimeInMillis += 1000
            if (remainingTimeInMillis >= 120 * 60 * 1000) {
                remainingTimeInMillis = 120 * 60 * 1000
            }
            updateCounterText()
            handler.postDelayed(this, 1000)
        }
    }

    fun startCounter(view: View) {
        if (isCountingDown) {
            handler.postDelayed(countDown, 1000)
        } else {
            handler.postDelayed(countUp, 1000)
        }
    }

    fun stopCounter(view: View) {
        handler.removeCallbacks(countDown)
        handler.removeCallbacks(countUp)
    }

    fun toggleCountingMode(view: View) {
        isCountingDown =!isCountingDown
        if (isCountingDown) {
            remainingTimeInMillis = 120 * 60 * 1000
        } else {
            remainingTimeInMillis = 0
        }
        updateCounterText()
    }

    private fun updateCounterText() {
        val minutes = (remainingTimeInMillis / (1000 * 60)).toInt()
        val seconds = (remainingTimeInMillis / 1000) % 60
        val timeText = String.format("%02d:%02d", minutes, seconds)
        counterTextView.text = timeText
    }

}

private fun Handler.postDelayed(focusCounter: FocusCounter, l: Long) {

}
