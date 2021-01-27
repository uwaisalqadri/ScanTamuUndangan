package com.masuwes.aplikasiundangan.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.zxing.integration.android.IntentIntegrator
import com.masuwes.aplikasiundangan.data.GuestViewModel
import com.masuwes.aplikasiundangan.databinding.ActivityMainBinding
import com.masuwes.aplikasiundangan.utils.Resource
import com.masuwes.aplikasiundangan.utils.showToast
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var guestAdapter: GuestAdapter
    private val viewModel: GuestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCouponList()

        binding.btnCheckCoupon.setOnClickListener {
            val coupon = binding.edtGuestName.text.toString()
            viewModel.postCouponCheck(coupon)
            Log.d("EditText", "$coupon")
        }

        viewModel.checkCouponData.observe(this, Observer { check ->
            when (check) {
                is Resource.Success -> {
                    binding.apply {
                        btnCheckCoupon.text = "Check"
                        postProgress.isVisible = false
                    }
                    check.data?.let { result ->
                        when (result.success) {
                            true -> {
                                viewModel.getCouponList()
                                "Coupon Berhasil Dicek".showToast(this)
                                "Kupon ${result.name} Berhasil Ditukarkan".showToast(this)
                            }
                            false -> {
                                "Coupon Tidak Terdaftar".showToast(this)
                            }
                        }
                    }
                }

                is Resource.Loading -> {
                    binding.apply {
                        btnCheckCoupon.text = ""
                        postProgress.isVisible = true
                    }
                }

                is Resource.Error -> {
                    binding.apply {
                        btnCheckCoupon.text = "Check"
                        postProgress.isVisible = false
                    }
                    "Gagal Menyambungkan ke server".showToast(this)
                }
            }
        })

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
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    "Barcode Tidak terdeteksi".showToast(this)
                } else {
                    binding.edtGuestName.setText(result.contents)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun showRecyclerView() {
        guestAdapter = GuestAdapter()

        viewModel.couponListData.observe(this@MainActivity, Observer { couponListData ->
            when(couponListData) {
                is Resource.Success -> {
                    binding.progressCircular.isVisible = false
                    couponListData.data?.let { result ->
                        guestAdapter.differ.submitList(result)
                    }
                }
                is Resource.Loading -> {
                    binding.progressCircular.isVisible = true
                }
                is Resource.Error -> {
                    binding.progressCircular.isVisible = false
                    "Gagal memuat data".showToast(this)
                }
            }
        })

        with(binding.rvGuest) {
            layoutManager = LinearLayoutManager(context)
            adapter = guestAdapter
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.action
//        }
//    }
}