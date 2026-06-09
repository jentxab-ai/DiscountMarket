package com.discountmarket.ui

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MarketplaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val titleText = TextView(this).apply {
            text = "بازار تخفیف‌ها"
            textSize = 22f
            setTextColor(0xFF1E1E1E.toInt())
        }
        rootLayout.addView(titleText)

        val messageText = TextView(this).apply {
            text = "\n\nبازار در نسخه بعدی فعال خواهد شد.\nمنتظر بمانید..."
            textSize = 16f
            setTextColor(0xFF757575.toInt())
        }
        rootLayout.addView(messageText)

        setContentView(rootLayout)

        Toast.makeText(this, "بازار در نسخه بعدی", Toast.LENGTH_SHORT).show()
    }
}
