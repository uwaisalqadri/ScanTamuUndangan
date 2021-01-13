package com.masuwes.aplikasiundangan.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masuwes.aplikasiundangan.data.model.Guest

class GuestViewModel(application: Application) : ViewModel() {

    private val guestRepository = GuestRepository(application)

    fun getAllGuest(): LiveData<List<Guest>> = guestRepository.getAllGuest()

    fun insertGuest(guest: Guest) {
        guestRepository.insert(guest)
    }

    fun deleteGuestByBarcode(guestBarcode: String) {
        guestRepository.delete(guestBarcode)
    }
}