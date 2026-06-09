package com.discountmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R

class CustomerDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_dashboard)

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val walletBalanceText = findViewById<TextView>(R.id.walletBalanceText)
        val uniqueIdText = findViewById<TextView>(R.id.uniqueIdText)
        val marketplaceBtn = findViewById<Button>(R.id.marketplaceBtn)
        val walletBtn = findViewById<Button>(R.id.walletBtn)
        val reportBtn = findViewById<Button>(R.id.reportBtn)
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)

        welcomeText.text = "خوش آمدید، مشتری 👤"
        walletBalanceText.text = "کیف پول: ۰ تومان"
        uniqueIdText.text = "شناسه: DM-0000-00000"

        marketplaceBtn.setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }

        walletBtn.setOnClickListener {
            Toast.makeText(this, "کیف پول در نسخه بعدی", Toast.LENGTH_SHORT).show()
        }

        reportBtn.setOnClickListener {
            Toast.makeText(this, "گزارشات در نسخه بعدی", Toast.LENGTH_SHORT).show()
        }

        logoutBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
