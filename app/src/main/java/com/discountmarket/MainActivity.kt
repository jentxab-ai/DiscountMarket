package com.discountmarket

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "بازار تخفیف\n\nاپ با موفقیت اجرا شد!"
        textView.textSize = 24f
        textView.setPadding(64, 64, 64, 64)
        setContentView(textView)
    }
}
