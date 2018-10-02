package com.learn.mock.network

import com.learn.mock.model.ProfileData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("test/profile")
    fun requestProfile(): Call<ProfileData>

}