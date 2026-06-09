package com.discountmarket.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R

class GenerateDiscountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_discount)

        val customerNameText = findViewById<TextView>(R.id.customerNameText)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val generateBtn = findViewById<Button>(R.id.generateBtn)

        val customerName = intent.getStringExtra("customerName") ?: "ناشناس"
        customerNameText.text = "مشتری: $customerName"

        generateBtn.setOnClickListener {
            val amount = amountInput.text.toString()
            if (amount.isNotEmpty()) {
                Toast.makeText(this, "تخفیف $amount تومانی با موفقیت صادر شد", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "لطفاً مبلغ را وارد کنید", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
