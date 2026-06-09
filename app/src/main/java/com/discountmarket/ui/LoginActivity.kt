package com.discountmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val typeGroup = findViewById<RadioGroup>(R.id.typeGroup)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val registerBtn = findViewById<Button>(R.id.goToRegisterBtn)

        loginBtn.setOnClickListener {
            val phone = phoneInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (phone.isEmpty() || password.length < 3) {
                Toast.makeText(this, "شماره و رمز عبور را وارد کنید", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "ورود موفق", Toast.LENGTH_SHORT).show()
        }

        registerBtn.setOnClickListener {
            Toast.makeText(this, "صفحه ثبت‌نام در قدم بعدی", Toast.LENGTH_SHORT).show()
        }
    }
}
