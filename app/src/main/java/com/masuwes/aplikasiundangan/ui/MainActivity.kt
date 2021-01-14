package com.masuwes.aplikasiundangan.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.google.zxing.integration.android.IntentIntegrator
import com.masuwes.aplikasiundangan.data.Guest
import com.masuwes.aplikasiundangan.databinding.ActivityMainBinding
import com.masuwes.aplikasiundangan.data.GuestViewModel
import com.masuwes.aplikasiundangan.utils.Constants
import com.masuwes.aplikasiundangan.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var guestAdapter: GuestAdapter
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = FirebaseRecyclerOptions.Builder<Guest>()
            .setQuery(
                FirebaseDatabase.getInstance().reference
                    .child(Constants.GUEST_TABLE),
                Guest::class.java
            )
            .build()

        guestAdapter = GuestAdapter(options)
        showRecyclerView()

        binding.apply {
            buttonScanNow.setOnClickListener {
                val scanner = IntentIntegrator(this@MainActivity)
                scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                scanner.setBeepEnabled(false)
                scanner.setOrientationLocked(false)
                scanner.initiateScan()
            }
        }

        databaseReference = FirebaseDatabase.getInstance().reference
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    id = (snapshot.childrenCount.toInt() until 100).random()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                error.message.showToast(this@MainActivity)
            }

        })
    }

    private fun createDataGuest(name: String, barcodeResult: String, couponsCount: Int) {
        val data = HashMap<String, Any>()
        data["id"] = id
        data["name"] = name
        data["barcodeResult"] = barcodeResult
        data["couponsCount"] = couponsCount

        databaseReference.child(Constants.GUEST_TABLE)
            .child(id.toString())
            .setValue(data)

        "Success".showToast(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    binding.tvScanResult.text = "Cancelled"
                } else {
                    binding.tvScanResult.text = "Scanned : " + result.contents
                    createDataGuest("Samsuddin", result.contents, 10)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun showRecyclerView() {
        binding.rvGuest.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = guestAdapter
            guestAdapter.notifyDataSetChanged()
        }
    }

    override fun onStart() {
        super.onStart()
        guestAdapter.startListening()
    }
}