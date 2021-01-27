package com.masuwes.aplikasiundangan.data.repository

import com.masuwes.aplikasiundangan.data.remote.RetrofitInstance

class GuestRepository {

    suspend fun getCouponList() = RetrofitInstance.api.getCouponList()

    suspend fun postCouponCheck(
        coupon: String
    ) = RetrofitInstance.api.postCouponCheck(coupon)

    suspend fun login(
        email: String
    ) = RetrofitInstance.api.login(email)
}