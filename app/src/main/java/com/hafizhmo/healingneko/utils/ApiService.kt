package com.hafizhmo.healingneko.utils

import com.hafizhmo.healingneko.data.Fact
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://catfact.ninja/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("fact")
    fun getFact(): Call<Fact>
}

object ApiClient{
    val retrofitFactService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}