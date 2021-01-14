package com.masuwes.aplikasiundangan.utils

import android.content.Context
import android.widget.Toast

object Constants {
    val GUEST_TABLE = "Guest"
}

fun String.showToast(context: Context) {
    Toast.makeText(
        context,
        this,
        Toast.LENGTH_SHORT
    ).show()
}