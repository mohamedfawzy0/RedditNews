package com.grandtask.redditnews.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.grandtask.redditnews.data.di.ServiceInterface
import com.grandtask.redditnews.presentation.model.Children
import com.grandtask.redditnews.presentation.model.DataItem
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ViewModelScoped
class ArticlesRepo @Inject constructor(private val service: ServiceInterface) {

    suspend fun getArticles(): Flow<ApiResult<List<Children>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = service.getArticles()
            emit(ApiResult.Success(response.data.children))
        } catch (e: IOException) {
            emit(ApiResult.Error(e.localizedMessage.orEmpty()))
        } catch (e: HttpException) {
            emit(ApiResult.Error(e.localizedMessage.orEmpty()))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.localizedMessage.orEmpty()))
        }
    }
}

sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<T>(val items: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}