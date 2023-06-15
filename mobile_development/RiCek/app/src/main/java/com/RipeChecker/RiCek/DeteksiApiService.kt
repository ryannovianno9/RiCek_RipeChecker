package com.RipeChecker.RiCek

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface DeteksiApiService {
    @Multipart
    @POST("/")
    fun postData(@Part img : MultipartBody.Part): Call<Response?>?
}