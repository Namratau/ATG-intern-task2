package com.example.atginterntask1.ui.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.atginterntask1.data.RetroService
import com.example.atginterntask1.pojo.Item
import com.example.atginterntask1.pojo.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class PhotoPagingSource @Inject constructor(val apiService : RetroService) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page  = params.key?:1
        return try{
            val data : List<Photo> = apiService.getDataFromAPI(page).awaitResponse().body()!!.photos.photo

            Log.d("TAG", "load: ${data.isNullOrEmpty()}")

            LoadResult.Page(data = data,
            prevKey = if(page == 1) null else page -1,
            nextKey = if(data.isEmpty()) null else page + 1)
        } catch (e:Exception){
            Log.d("TAG", "load: ${e.message}")
            LoadResult.Error(e)
        }

    }
}