package com.hafizhmo.healingneko.data

import com.hafizhmo.healingneko.data.local.dao.FactDao
import com.hafizhmo.healingneko.data.local.entity.FactEntity
import com.hafizhmo.healingneko.data.remote.network.ApiClient
import com.hafizhmo.healingneko.data.remote.response.FactResponse
import javax.inject.Inject

class FactRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val factDao: FactDao
) {

    suspend fun getFact(): FactResponse? {
        val request = apiClient.getFact()

        if (!request.isSuccessful)
            return null

        if (request.failed)
            return null

        return request.body
    }

    suspend fun saveFact(factEntity: FactEntity) {
        factDao.insertFact(factEntity)
    }

    suspend fun removeFact(fact: FactEntity) {
        factDao.deleteFact(fact)
    }

    suspend fun getFactsFromDatabase(): List<FactEntity> {
        return factDao.getAllFact()
    }

}