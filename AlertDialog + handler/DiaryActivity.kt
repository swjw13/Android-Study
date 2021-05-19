package com.jw.project3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val diaryEditText: EditText by lazy{
        findViewById<EditText>(R.id.diaryEditText)
    }

    private val handler = Handler(Looper.getMainLooper())   // main thread와 연결된 handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val detailPreference = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText
        diaryEditText.setText(detailPreference.getString("detail", ""))

        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit{
                putString("detail", diaryEditText.text.toString())
            }
        }

        diaryEditText.addTextChangedListener {
            handler.removeCallbacks(runnable)       // 이전에 있던 handler객체를 바꿔줌
            handler.postDelayed(runnable, 500)
        }
    }
}