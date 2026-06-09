package com.discountmarket.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QrScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        tv.text = "اسکنر QR\n\nدر نسخه بعدی فعال می‌شود."
        tv.textSize = 20f
        tv.setPadding(32, 32, 32, 32)
        setContentView(tv)
    }
}
