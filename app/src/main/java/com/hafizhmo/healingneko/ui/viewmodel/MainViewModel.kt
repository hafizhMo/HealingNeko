package com.hafizhmo.healingneko.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizhmo.healingneko.data.FactRepository
import com.hafizhmo.healingneko.data.local.entity.FactEntity
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
    val factIsSaved = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshFact() {
        viewModelScope.launch {
            loading.postValue(true)
            val response = repository.getFact()

            if (!response?.fact.isNullOrEmpty()){
                _factLiveData.postValue(response)
                factIsSaved.postValue(false)
                loading.postValue(false)
            }
        }
    }

    fun saveFact(fact: String){
        viewModelScope.launch {
            repository.saveFact(FactEntity(fact))
            factIsSaved.postValue(true)
        }
    }

    fun removeFact(fact: String){
        viewModelScope.launch {
            repository.removeFact(FactEntity(fact))
            factIsSaved.postValue(false)
        }
    }
}