package com.example.atginterntask1.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.atginterntask1.adapters.SearchAdapter
import com.example.atginterntask1.data.RetroService
import com.example.atginterntask1.pojo.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel  @Inject constructor(private val retroService : RetroService, val repository: SearchRepository) : ViewModel() {

    private var recyclerListData : MutableLiveData<List<Photo>> = MutableLiveData()

    private val TAG = "TAG"

    fun onTextChanged(s: String){
        Log.d(TAG, "onTextChanged: ${s}")
        recyclerListData.value = repository.SearchImageFromApi(s, retroService).value
        Log.d(TAG, "onTextChanged: recylerListData : ${recyclerListData.value?.size}")
    }

    fun recyclerListDataObserver() : LiveData<List<Photo>> {
        return recyclerListData
    }

}
