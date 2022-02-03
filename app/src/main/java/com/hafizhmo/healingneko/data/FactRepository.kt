package com.hafizhmo.healingneko.data

import com.hafizhmo.healingneko.data.remote.network.ApiClient
import com.hafizhmo.healingneko.data.remote.response.FactResponse
import javax.inject.Inject

class FactRepository @Inject constructor(
    private val apiClient: ApiClient
){

    suspend fun getFact(): FactResponse? {
        val request = apiClient.getFact()

        if(!request.isSuccessful)
            return null

        if (request.failed)
            return null

        return request.body
    }

}