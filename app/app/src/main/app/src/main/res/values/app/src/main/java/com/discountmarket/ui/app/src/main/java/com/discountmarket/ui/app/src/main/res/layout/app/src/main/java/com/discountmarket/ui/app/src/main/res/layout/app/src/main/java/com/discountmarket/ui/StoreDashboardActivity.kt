package com.discountmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R

class StoreDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_dashboard)

        findViewById<TextView>(R.id.welcomeText).text = "داشبورد فروشگاه"
        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
