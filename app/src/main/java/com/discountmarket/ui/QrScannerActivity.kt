package com.discountmarket.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.discountmarket.R

class QrScannerActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)

        barcodeView = findViewById(R.id.barcode_scanner)

        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                if (result != null) {
                    barcodeView.pause()
                    val scannedData = result.text

                    if (scannedData != null && scannedData.startsWith("DM-")) {
                        val intent = Intent(this@QrScannerActivity, GenerateDiscountActivity::class.java)
                        intent.putExtra("customerId", scannedData)
                        intent.putExtra("customerName", "مشتری")
                        startActivity(intent)
                        finish()
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
