package com.hafizhmo.healingneko.data

import com.hafizhmo.healingneko.data.remote.response.FactResponse
import com.hafizhmo.healingneko.di.NetworkModule

class FactRepository {

    suspend fun getFact(): FactResponse? {
        val request = NetworkModule.apiClient.getFact()

        if(!request.isSuccessful)
            return null

        if (request.failed)
            return null

        return request.body
    }

}