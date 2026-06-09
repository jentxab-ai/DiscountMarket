package com.discountmarket.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.discountmarket.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class GenerateDiscountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_discount)

        val customerNameText = findViewById<TextView>(R.id.customerNameText)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val generateBtn = findViewById<Button>(R.id.generateBtn)

        val customerId = intent.getStringExtra("customerId") ?: ""
        val customerName = intent.getStringExtra("customerName") ?: "ناشناس"

        customerNameText.text = "مشتری: $customerName"

        generateBtn.setOnClickListener {
            val amount = amountInput.text.toString().toLongOrNull()
            if (amount == null || amount < 10000) {
                Toast.makeText(this, "مبلغ تخفیف حداقل ۱۰,۰۰۰ تومان باشد", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val storeId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            val firestore = FirebaseFirestore.getInstance()

            // دریافت نام فروشگاه
            firestore.collection("users").document(storeId).get()
                .addOnSuccessListener { storeDoc ->
                    val storeName = storeDoc.getString("name") ?: "فروشگاه"

                    val discountId = firestore.collection("discounts").document().id
                    val transactionId = "TRX-${storeId.take(6)}-${customerId.take(6)}-${System.currentTimeMillis()}-${UUID.randomUUID().toString().take(6).uppercase()}"

                    val discountData = hashMapOf(
                        "id" to discountId,
                        "storeId" to storeId,
                        "storeName" to storeName,
                        "customerId" to customerId,
                        "amount" to amount,
                        "originalAmount" to amount,
                        "transactionId" to transactionId,
                        "status" to "ACTIVE",
                        "isForSale" to false,
                        "salePrice" to 0L,
                        "createdAt" to System.currentTimeMillis(),
                        "expiryDate" to (System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000),
                        "digitalHash" to UUID.randomUUID().toString()
                    )

                    firestore.collection("discounts").document(discountId)
                        .set(discountData)
                        .addOnSuccessListener {
                            Toast.makeText(this, "تخفیف با موفقیت صادر شد\nکد پیگیری: $transactionId", Toast.LENGTH_LONG).show()
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "خطا: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
        }
    }
}
