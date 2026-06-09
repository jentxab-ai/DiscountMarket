package com.discountmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R

class CustomerDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_dashboard)

        findViewById<TextView>(R.id.welcomeText).text = "خوش آمدید، مشتری"
        findViewById<TextView>(R.id.walletBalanceText).text = "کیف پول: ۰ تومان"
        findViewById<TextView>(R.id.uniqueIdText).text = "شناسه: DM-0000-00000"

        findViewById<Button>(R.id.marketplaceBtn).setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }
        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
