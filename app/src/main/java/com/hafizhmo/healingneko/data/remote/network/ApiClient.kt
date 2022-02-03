package com.hafizhmo.healingneko.data.remote.network

import com.hafizhmo.healingneko.data.remote.response.FactResponse
import com.hafizhmo.healingneko.data.remote.response.SimpleResponse
import retrofit2.Response
import java.lang.Exception

class ApiClient(
    private val apiService: ApiService
) {

    suspend fun getFact(): SimpleResponse<FactResponse> {
        return safeApiCall { apiService.getFact() }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }

}