package com.grandtask.redditnews.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grandtask.redditnews.data.repository.ApiResult
import com.grandtask.redditnews.data.repository.ArticlesRepo
import com.grandtask.redditnews.presentation.model.Children
import com.grandtask.redditnews.presentation.model.DataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ArticlesRepo) : ViewModel() {

    private var _isLoadingLivData: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoadingLivData

    private val _articleLiveData: MutableLiveData<List<Children>?> = MutableLiveData()
    val articleLiveData: LiveData<List<Children>?> = _articleLiveData

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            repository
                .getArticles()
                .flowOn(Dispatchers.IO)
                .onEach {
                    when (it) {
                        is ApiResult.Error -> {
                            _isLoadingLivData.value = false
                            _errorMessage.value = it.message

                        }
                        ApiResult.Loading -> {
                            _isLoadingLivData.value = true
                        }
                        is ApiResult.Success -> {
                            _isLoadingLivData.value = false
                            _articleLiveData.value = it.items
                        }
                    }
                }
                .collect()
        }
    }
}
