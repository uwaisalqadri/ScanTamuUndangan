package com.masuwes.aplikasiundangan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.masuwes.aplikasiundangan.R
import com.masuwes.aplikasiundangan.data.GuestViewModel
import com.masuwes.aplikasiundangan.databinding.ActivityLoginBinding
import com.masuwes.aplikasiundangan.utils.Resource
import com.masuwes.aplikasiundangan.utils.showToast

class LoginActivity : AppCompatActivity() {

    private val viewModel: GuestViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.edtGuestName.text.toString()
            viewModel.login(email)
        }

        viewModel.loginData.observe(this, Observer { loginData ->
            when(loginData) {
                is Resource.Success -> {
                    binding.apply {
                        pgLogin.isVisible = false
                        btnLogin.text = "Login"
                    }
                    "Berhasil Login".showToast(this)
                    startActivity(Intent(this, MainActivity::class.java))
                }
                is Resource.Loading -> {
                    binding.apply {
                        pgLogin.isVisible = true
                        btnLogin.text = ""
                    }
                }

                is Resource.Error -> {
                    binding.apply {
                        pgLogin.isVisible = false
                        btnLogin.text = "Login"
                        "Gagal Login".showToast(this@LoginActivity)
                    }
                }
            }
        })
    }
}