package com.discountmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R

class StoreDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_dashboard)

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val generateBtn = findViewById<Button>(R.id.generateDiscountBtn)
        val marketplaceBtn = findViewById<Button>(R.id.marketplaceBtn)
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)

        welcomeText.text = "خوش آمدید، فروشگاه 🏪"

        generateBtn.setOnClickListener {
            startActivity(Intent(this, QrScannerActivity::class.java))
        }

        marketplaceBtn.setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }

        logoutBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
