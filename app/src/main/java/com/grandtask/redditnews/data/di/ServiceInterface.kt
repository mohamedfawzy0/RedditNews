package com.grandtask.redditnews.data.di

import com.grandtask.redditnews.presentation.model.Articles
import com.grandtask.redditnews.presentation.model.DataItem
import retrofit2.Response
import retrofit2.http.GET

interface ServiceInterface {

    @GET("r/kotlin/.json")
    suspend fun getArticles(): Articles
}