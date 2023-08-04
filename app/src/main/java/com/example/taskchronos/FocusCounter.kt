package com.example.taskchronos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView

private lateinit var counterTextView: TextView
private lateinit var startButton: Button
private val handler = Handler()
private var isCountingDown = true
private var remainingTimeInMillis = 120 * 60 * 1000



class FocusCounter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_counter)

        counterTextView = findViewById(R.id.counter_text_view)
        startButton = findViewById(R.id.start_button)
        updateCounterText()
    }

    private val countDown = object : Runnable {
        override fun run() {
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
    }

    private val countUp = object : Runnable {
        override fun run() {
            if (!isCountingDown) {
                remainingTimeInMillis += 1000
                if (remainingTimeInMillis >= 120 * 60 * 1000) {
                    remainingTimeInMillis = 120 * 60 * 1000
                }
                updateCounterText()
                handler.postDelayed(this, 1000)
            }
        }
    }

    fun startCounter(view: View) {
        handler.removeCallbacks(countUp)
        if (isCountingDown) {
            handler.postDelayed(countDown, 1000)
        } else {
            handler.removeCallbacks(countDown) // Sayaç durduğunda, geri sayımı durdurun
            handler.removeCallbacks(countUp) // Sayaç durduğunda, sayımı durdurun
            handler.postDelayed(countUp, 1000)
        }
        isCountingDown = !isCountingDown // Durumu tersine çeviriyoruz (Başlama -> Durdurma, Durdurma -> Başlama)

    }

    fun stopCounter(view: View) {
        handler.removeCallbacks(countDown)
        handler.removeCallbacks(countUp)
    }

    fun toggleCountingMode(view: View) {
        isCountingDown = !isCountingDown
        if (isCountingDown) {
            remainingTimeInMillis = 120 * 60 * 1000
        } else {
            remainingTimeInMillis = 0
        }
        updateCounterText()
       // startButton.text = if (isCountingDown) "Başla" else "Durdur" // Sayacı değiştirme durumunda buton metnini uygun bir şekilde güncelliyoruz
    }

    private fun updateCounterText() {
        val minutes = (remainingTimeInMillis / (1000 * 60)).toInt()
        val seconds = (remainingTimeInMillis / 1000) % 60
        val timeText = String.format("%02d:%02d", minutes, seconds)
        counterTextView.text = timeText
    }

    fun resetCounter(view: View) {
        handler.removeCallbacks(countDown) // Eğer sayaç çalışıyorsa durduralım
        handler.removeCallbacks(countUp)

        isCountingDown = true // Sayaç varsayılan olarak geri sayıma başlasın
        remainingTimeInMillis = 120 * 60 * 1000 // Varsayılan süreyi 120 dakika olarak ayarlayalım
        updateCounterText()


        if (isCountingDown) {
            handler.postDelayed(countDown, 1000) // Sayaç geri sayımı başlatalım
           // startButton.text = "Durdur" // Başlatma durumuna döndürerek sayaç başlasın

        } else {
            //startButton.text = "Başla" // Durma durumunda buton metnini "Başla" olarak güncelliyoruz
            handler.postDelayed(countUp, 1000) // Sayaç sayımı başlatalım
        }

    }
}
