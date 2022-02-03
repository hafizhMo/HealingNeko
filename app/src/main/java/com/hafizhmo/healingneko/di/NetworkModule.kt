package com.hafizhmo.healingneko.di

import com.hafizhmo.healingneko.data.remote.network.ApiClient
import com.hafizhmo.healingneko.data.remote.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val apiClient = ApiClient(apiService)
}