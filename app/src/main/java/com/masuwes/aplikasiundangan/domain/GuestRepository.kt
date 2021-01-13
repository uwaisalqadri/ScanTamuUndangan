package com.masuwes.aplikasiundangan.domain

import android.app.Application
import androidx.lifecycle.LiveData
import com.masuwes.aplikasiundangan.data.local.GuestDao
import com.masuwes.aplikasiundangan.data.local.GuestDatabase
import com.masuwes.aplikasiundangan.data.model.Guest
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GuestRepository(application: Application) {

    private val guestDao: GuestDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = GuestDatabase.getDatabase(application)
        guestDao = db.guestDao()
    }

    fun getAllGuest(): LiveData<List<Guest>> = guestDao.getAllGuests()

    fun insert(guest: Guest) {
        executorService.execute { guestDao.insert(guest) }
    }

    fun delete(guestBarcode: String) {
        executorService.execute { guestDao.deleteGuest(guestBarcode) }
    }
}