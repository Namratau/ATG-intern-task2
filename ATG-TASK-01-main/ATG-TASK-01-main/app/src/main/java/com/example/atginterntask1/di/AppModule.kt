package com.example.atginterntask1.di

import com.example.atginterntask1.data.RetroService
import com.example.atginterntask1.ui.search.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.flickr.com/services/rest/"
    @Provides
    fun getRetrofitService(retrofit:Retrofit) : RetroService {
        return retrofit.create(RetroService::class.java)
    }
    @Provides
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun getSearchRepository() : SearchRepository{
        return SearchRepository()
    }

}