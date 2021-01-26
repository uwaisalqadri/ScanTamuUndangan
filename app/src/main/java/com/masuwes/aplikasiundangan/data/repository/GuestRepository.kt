package com.masuwes.aplikasiundangan.data.repository

import com.masuwes.aplikasiundangan.data.remote.RetrofitInstance

class GuestRepository {

    suspend fun getCouponList() = RetrofitInstance.api.getCouponList()

    suspend fun postCouponCheck() = RetrofitInstance.api.postCouponCheck()
}