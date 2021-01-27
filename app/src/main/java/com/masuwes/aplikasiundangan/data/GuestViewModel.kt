package com.masuwes.aplikasiundangan.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.aplikasiundangan.data.model.CheckCouponResponse
import com.masuwes.aplikasiundangan.data.model.GuestResponse
import com.masuwes.aplikasiundangan.data.model.LoginResponse
import com.masuwes.aplikasiundangan.data.repository.GuestRepository
import com.masuwes.aplikasiundangan.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GuestViewModel : ViewModel() {

    private val guestRepository: GuestRepository = GuestRepository()
    val couponListData: MutableLiveData<Resource<GuestResponse>> = MutableLiveData()
    val checkCouponData: MutableLiveData<Resource<CheckCouponResponse>> = MutableLiveData()
    val loginData: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun getCouponList() = viewModelScope.launch {
        couponListData.postValue(Resource.Loading())
        val response = guestRepository.getCouponList()
        couponListData.postValue(handleCouponList(response))
    }

    fun postCouponCheck(coupon: String) = viewModelScope.launch {
        checkCouponData.postValue(Resource.Loading())
        val response = guestRepository.postCouponCheck(coupon)
        checkCouponData.postValue(handleCheckCoupon(response))
    }

    fun login(email: String) = viewModelScope.launch {
        loginData.postValue(Resource.Loading())
        val response = guestRepository.login(email)
        loginData.postValue(handleLogin(response))
    }

    private fun handleCouponList(response: Response<GuestResponse>) : Resource<GuestResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCheckCoupon(response: Response<CheckCouponResponse>) : Resource<CheckCouponResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleLogin(response: Response<LoginResponse>) : Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}