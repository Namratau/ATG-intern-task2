package com.example.atginterntask1.data

import com.example.atginterntask1.pojo.Item
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("?method=flickr.photos.getRecent&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s&per_page=20")
    fun getDataFromAPI(@Query("page")page:Int) : Call<Item>

    @GET("?method=flickr.photos.getRecent&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
    fun getSearchImageFromAPI(@Query("text")search : String) : Call<Item>
}