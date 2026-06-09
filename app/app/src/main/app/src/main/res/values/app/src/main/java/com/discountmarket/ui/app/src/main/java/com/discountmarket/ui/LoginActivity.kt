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
            val type = if (typeGroup.checkedRadioButtonId == R.id.storeRadio) "store" else "customer"

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "لطفاً همه فیلدها را پر کنید", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ورود ساده بدون Firebase برای تست
            if (password.length >= 3) {
                val intent = when (type) {
                    "store" -> Intent(this, StoreDashboardActivity::class.java)
                    "customer" -> Intent(this, CustomerDashboardActivity::class.java)
                    else -> null
                }
                if (intent != null) {
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "رمز عبور حداقل ۳ کاراکتر باشد", Toast.LENGTH_SHORT).show()
            }
        }

        registerBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
