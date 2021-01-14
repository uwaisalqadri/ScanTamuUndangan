package com.masuwes.aplikasiundangan.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Guest(
    var id: Int = 0,
    var barcodeResult: String? = null,
    var name: String? = null,
    var couponsCount: Int = 0
) : Parcelable
