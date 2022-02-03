package com.hafizhmo.healingneko.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizhmo.healingneko.data.FactRepository
import com.hafizhmo.healingneko.data.remote.response.FactResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = FactRepository()

    private val _factLiveData = MutableLiveData<FactResponse>()
    val factLiveData: MutableLiveData<FactResponse> = _factLiveData

    fun refreshFact() {
        viewModelScope.launch {
            val response = repository.getFact()
            _factLiveData.postValue(response)
        }
    }
}