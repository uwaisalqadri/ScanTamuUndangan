package com.masuwes.aplikasiundangan.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masuwes.aplikasiundangan.data.model.Guest

@Dao
interface GuestDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(guest: Guest)

    @Query("DELETE FROM guest_table WHERE barcodeResult=:barcodeResult")
    fun deleteGuest(barcodeResult: String)

    @Query("SELECT * from guest_table ORDER BY id ASC")
    fun getAllGuests(): LiveData<List<Guest>>
}