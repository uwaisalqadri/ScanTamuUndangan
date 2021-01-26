package com.masuwes.aplikasiundangan.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masuwes.aplikasiundangan.data.model.GuestResponse
import com.masuwes.aplikasiundangan.data.repository.GuestRepository
import com.masuwes.aplikasiundangan.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GuestViewModel : ViewModel() {

    private val guestRepository: GuestRepository = GuestRepository()
    val couponListData: MutableLiveData<Resource<GuestResponse>> = MutableLiveData()

    fun getCouponList() = viewModelScope.launch {
        couponListData.postValue(Resource.Loading())
        val response = guestRepository.getCouponList()
        couponListData.postValue(handleCouponList(response))
    }

    fun postCouponCheck() = viewModelScope.launch {
        val response = guestRepository.postCouponCheck()
    }

    private fun handleCouponList(response: Response<GuestResponse>) : Resource<GuestResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}