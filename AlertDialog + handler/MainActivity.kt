package com.jw.project3

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = 0
                maxValue = 0
            }
    }
    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3).apply {
            minValue = 0
            maxValue = 0
        }
    }
    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3).apply {
            minValue = 0
            maxValue = 0
        }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }
    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private var changePaddwordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {

            if (changePaddwordMode) {
                Toast.makeText(this, "비밀번호 변경중입니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreference =
                getSharedPreferences("password", Context.MODE_PRIVATE)  // mode를 변경하면 다른 파일들과 공유

            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                // 패스워드 성공
                startActivity(Intent(this, DiaryActivity::class.java))
            } else {
                // 실패
                AlertDialog.Builder(this)
                    .setTitle("실패!")
                    .setMessage("비밀번호가 잘못되었습니다")
                    .setPositiveButton("확인") { _, _ -> }
                    .create()
                    .show()
            }
        }

        changePasswordButton.setOnClickListener {

            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (changePaddwordMode) {
                val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)

                passwordPreference.edit(true) {
                    putString("password", passwordFromUser)
                }
                changePaddwordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)

            } else {
                val passwordPreference =
                    getSharedPreferences("password", Context.MODE_PRIVATE)  // mode를 변경하면 다른 파일들과 공유

                if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                    changePaddwordMode = true
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
                    changePasswordButton.setBackgroundColor(Color.RED)
                } else {
                    // 실패
                    AlertDialog.Builder(this)
                        .setTitle("실패!")
                        .setMessage("비밀번호가 잘못되었습니다")
                        .setPositiveButton("확인") { _, _ -> }
                        .create()
                        .show()
                }
            }
        }

    }
}