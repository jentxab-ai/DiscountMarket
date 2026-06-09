package com.discountmarket.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class QrScannerActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.discountmarket.R.layout.activity_qr_scanner)

        barcodeView = findViewById(com.discountmarket.R.id.barcode_scanner)

        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                if (result != null) {
                    barcodeView.pause()
                    val scannedData = result.text

                    // انتظار داریم کیوآرکد شامل شناسه مشتری باشد
                    if (scannedData != null && scannedData.startsWith("DM-")) {
                        // پیدا کردن مشتری با این شناسه
                        FirebaseFirestore.getInstance()
                            .collection("users")
                            .whereEqualTo("uniqueId", scannedData)
                            .get()
                            .addOnSuccessListener { docs ->
                                if (!docs.isEmpty) {
                                    val customer = docs.documents[0]
                                    val customerId = customer.id

                                    // رفتن به صفحه ساخت تخفیف
                                    val intent = android.content.Intent(this@QrScannerActivity, GenerateDiscountActivity::class.java)
                                    intent.putExtra("customerId", customerId)
                                    intent.putExtra("customerName", customer.getString("name"))
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@QrScannerActivity, "مشتری یافت نشد", Toast.LENGTH_SHORT).show()
                                    barcodeView.resume()
                                }
                            }
                    } else {
                        Toast.makeText(this@QrScannerActivity, "کیوآرکد نامعتبر", Toast.LENGTH_SHORT).show()
                        barcodeView.resume()
                    }
                }
            }

            override fun possibleResultPoints(resultPoints: List<com.google.zxing.ResultPoint>?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}
