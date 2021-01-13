package com.masuwes.aplikasiundangan.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "guest_table")
@Parcelize
data class Guest(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "barcodeResult")
    var barcodeResult: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "couponsCount")
    var couponsCount: Int
) : Parcelable
