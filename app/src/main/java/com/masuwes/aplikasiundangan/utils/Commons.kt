package com.masuwes.aplikasiundangan.utils

import android.content.Context
import android.widget.Toast

object Constants {
    const val GUEST_TABLE = "Guest"
    const val BASE_URL = "https://internal.wedding.indi.id/"
}

fun String.showToast(context: Context) {
    Toast.makeText(
        context,
        this,
        Toast.LENGTH_SHORT
    ).show()
}