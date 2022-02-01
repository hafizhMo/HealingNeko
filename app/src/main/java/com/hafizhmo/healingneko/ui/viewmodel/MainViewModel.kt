package com.hafizhmo.healingneko.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafizhmo.healingneko.data.FactRepository
import com.hafizhmo.healingneko.data.remote.FactResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var factModel : MutableLiveData<FactResponse>? = null

    fun fetchData(): LiveData<FactResponse>? {
        factModel = FactRepository().getFactResponse()
        return factModel
    }
}