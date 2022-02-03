package com.hafizhmo.healingneko.data.remote.network

import com.hafizhmo.healingneko.data.remote.response.FactResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("fact")
    suspend fun getFact(): Response<FactResponse>
}