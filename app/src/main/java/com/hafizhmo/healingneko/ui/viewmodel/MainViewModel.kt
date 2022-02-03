package com.hafizhmo.healingneko.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizhmo.healingneko.data.FactRepository
import com.hafizhmo.healingneko.data.remote.response.FactResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FactRepository
) : ViewModel() {

    private val _factLiveData = MutableLiveData<FactResponse?>()
    val factLiveData: MutableLiveData<FactResponse?> = _factLiveData

    fun refreshFact() {
        viewModelScope.launch {
            val response = repository.getFact()
            _factLiveData.postValue(response)
        }
    }
}