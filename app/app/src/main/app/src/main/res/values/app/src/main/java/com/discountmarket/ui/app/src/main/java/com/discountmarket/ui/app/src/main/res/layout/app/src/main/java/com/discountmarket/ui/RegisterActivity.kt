package com.discountmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.registerBtn).setOnClickListener {
            val name = findViewById<EditText>(R.id.nameInput).text.toString()
            val phone = findViewById<EditText>(R.id.phoneInput).text.toString()
            val password = findViewById<EditText>(R.id.passwordInput).text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty() && password.length >= 3) {
                Toast.makeText(this, "ثبت‌نام موفق", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "لطفاً همه فیلدها را پر کنید", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
