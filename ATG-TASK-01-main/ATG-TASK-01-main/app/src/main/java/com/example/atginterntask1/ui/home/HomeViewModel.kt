package com.example.atginterntask1.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.atginterntask1.data.RetroService
import com.example.atginterntask1.pojo.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val retroService : RetroService) : ViewModel() {
    val characters: Flow<PagingData<Photo>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { PhotoPagingSource(retroService) }
        ).flow.cachedIn(viewModelScope)
}