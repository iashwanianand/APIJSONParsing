package com.example.apijsonparsing.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val API_TIMEOUT_SECONDS: Long = 120
    private var BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: ApiInterface? = getClient()?.create(ApiInterface::class.java)
    /**
     * @description Method is used to get instance of Retrofit after client setup .
     */
    fun getClient(): Retrofit? {
        val builder = getBuilder()
        builder?.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builder!!.build())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(getFactory())
            .build()
    }

    private fun getBuilder(): OkHttpClient.Builder? {
        return OkHttpClient().newBuilder()
            .connectTimeout(API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }

    /**
     * @description Method is used to return the GsonConverterFactory.
     */
    private fun getFactory(): Converter.Factory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }

}