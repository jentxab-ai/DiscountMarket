package com.discountmarket.ui

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MarketplaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // ساخت UI بصورت برنامه‌نویسی برای سادگی
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
        
        val recycler = RecyclerView(this)
        rootLayout.addView(recycler, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0, 1f
        ))
        
        setContentView(rootLayout)
        
        // بارگذاری تخفیف‌های قابل فروش از Firestore
        val firestore = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        recycler.layoutManager = LinearLayoutManager(this)
        
        firestore.collection("discounts")
            .whereEqualTo("isForSale", true)
            .whereEqualTo("status", "ACTIVE")
            .addSnapshotListener { snapshots, _ ->
                if (snapshots != null) {
                    val items = snapshots.documents.map { doc ->
                        MarketItem(
                            id = doc.id,
                            transactionId = doc.getString("transactionId") ?: "",
                            storeName = doc.getString("storeName") ?: "",
                            amount = doc.getLong("amount") ?: 0,
                            salePrice = (doc.getLong("amount") ?: 0) / 2,
                            ownerId = doc.getString("customerId") ?: ""
                        )
                    }
                    recycler.adapter = MarketAdapter(items) { item ->
                        buyItem(item, userId)
                    }
                }
            }
    }
    
    private fun buyItem(item: MarketItem, buyerId: String) {
        val firestore = FirebaseFirestore.getInstance()
        
        // انتقال مالکیت
        firestore.collection("discounts").document(item.id)
            .update(
                mapOf(
                    "customerId" to buyerId,
                    "isForSale" to false,
                    "salePrice" to 0L,
                    "expiryDate" to (System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000)
                )
            )
            .addOnSuccessListener {
                Toast.makeText(this, "تخفیف با موفقیت خریداری شد", Toast.LENGTH_SHORT).show()
            }
    }
}

data class MarketItem(
    val id: String,
    val transactionId: String,
    val storeName: String,
    val amount: Long,
    val salePrice: Long,
    val ownerId: String
)

class MarketAdapter(
    private val items: List<MarketItem>,
    private val onBuy: (MarketItem) -> Unit
) : RecyclerView.Adapter<MarketAdapter.VH>() {
    
    class VH(val view: LinearLayout) : RecyclerView.ViewHolder(view)
    
    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH {
        val layout = LinearLayout(parent.context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        return VH(layout)
    }
    
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val ctx = holder.view.context
        holder.view.removeAllViews()
        
        holder.view.addView(TextView(ctx).apply {
            text = "فروشگاه: ${item.storeName}"
            setTextColor(0xFF1E1E1E.toInt())
            textSize = 14f
        })
        holder.view.addView(TextView(ctx).apply {
            text = "مبلغ تخفیف: ${item.amount} تومان"
            setTextColor(0xFF333333.toInt())
            textSize = 16f
        })
        holder.view.addView(TextView(ctx).apply {
            text = "قیمت فروش: ${item.salePrice} تومان (۵۰٪)"
            setTextColor(0xFF4CAF50.toInt())
            textSize = 16f
        })
        holder.view.addView(Button(ctx).apply {
            text = "خرید"
            setTextColor(0xFFFFFFFF.toInt())
            setBackgroundColor(0xFF1E1E1E.toInt())
            setOnClickListener { onBuy(item) }
        })
    }
    
    override fun getItemCount() = items.size
}
