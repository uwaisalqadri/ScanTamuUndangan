package com.masuwes.aplikasiundangan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masuwes.aplikasiundangan.R
import com.masuwes.aplikasiundangan.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}