package com.masuwes.aplikasiundangan.data.remote

import com.masuwes.aplikasiundangan.data.model.CheckCouponResponse
import com.masuwes.aplikasiundangan.data.model.GuestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/coupon/list")
    suspend fun getCouponList() : Response<GuestResponse>

    @POST("api/coupon/check")
    suspend fun postCouponCheck() : Response<CheckCouponResponse>
}