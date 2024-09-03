package com.example.moviesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.datasource.remote.MoviesPagingSource
import com.example.domain.models.MovieDetailsResponse
//import com.example.data.usecases.GetMoviesUseCase
import com.example.domain.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
):ViewModel() {

    init {
        loadDetailsMovie(3)
    }

    val movies = Pager(
            config = PagingConfig(1),
            pagingSourceFactory = { MoviesPagingSource(moviesRepository) }
    ).flow.cachedIn(viewModelScope)

    val detailsMovie : MutableStateFlow<MovieDetailsResponse>? = null

    private fun loadDetailsMovie(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        val response = moviesRepository.getMovieDetails(id)
        if(response.isSuccessful){
            detailsMovie?.value = response.body()!!
        }

    }

}
