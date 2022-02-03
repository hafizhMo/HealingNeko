package com.hafizhmo.healingneko.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizhmo.healingneko.data.FactRepository
import com.hafizhmo.healingneko.data.local.entity.FactEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: FactRepository
) : ViewModel() {

    private val _factLiveData = MutableLiveData<List<FactEntity>?>()
    val factLiveData: MutableLiveData<List<FactEntity>?> = _factLiveData
    private val factIsSaved = MutableLiveData<Boolean>()

    fun loadFactFromDatabase() {
        viewModelScope.launch {
            val response = repository.getFactsFromDatabase()
            _factLiveData.postValue(response)
            factIsSaved.postValue(true)
        }
    }

    fun removeFact(fact: FactEntity){
        viewModelScope.launch {
            repository.removeFact(fact)
            factIsSaved.postValue(false)
        }
    }
}