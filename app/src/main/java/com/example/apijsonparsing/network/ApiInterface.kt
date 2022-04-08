package com.example.apijsonparsing.network

import com.example.apijsonparsing.model.PhotoDataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    fun getPhotosList():
            Call<PhotoDataModel>
}