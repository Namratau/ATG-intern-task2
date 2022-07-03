package com.example.atginterntask1.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.atginterntask1.data.RetroService
import com.example.atginterntask1.pojo.Item
import com.example.atginterntask1.pojo.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {

    private var apiResponse = MutableLiveData<List<Photo>>()

    private val TAG = "TAG"

    fun SearchImageFromApi(search : String, retroService: RetroService) : MutableLiveData<List<Photo>> {
        Log.d(TAG, "SearchImageFromApi: $search")
        retroService.getSearchImageFromAPI(search).enqueue(object  : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                val res : Item? = response.body()
                if (res != null) {
                    if(!res.photos.photo.isNullOrEmpty()){
                        Log.d(TAG, "onResponse: ${res.photos.photo.size}" )
                        apiResponse.value = res.photos.photo
                    }
                }

            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
        return apiResponse
    }


}