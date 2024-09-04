package com.example.moviesapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.datasource.remote.MoviesPagingSource
import com.example.domain.models.MovieDetailsResponse
import com.example.domain.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val movies = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { MoviesPagingSource(moviesRepository) }
    ).flow.cachedIn(viewModelScope)

    var detailsMovie: MutableLiveData<MovieDetailsResponse> = MutableLiveData()

    fun loadDetailsMovie(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val response = moviesRepository.getMovieDetails(id)
            Log.d("TAG1000", "loadDetailsMovie: "+response)
            withContext(Dispatchers.Main){detailsMovie.value = response.body()}
            Log.d("TAG1000", "loadDetailsMovie: "+response.body())
            Log.d("TAG1000", "loadDetailsMovie: "+detailsMovie)
        }
    }

}
