package com.example.moviesapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding
import com.example.moviesapp.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity :AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getLongExtra("movie_id", 533535L)
        lifecycleScope.launch(Dispatchers.Main) {
            Log.d("TAG1000", "movieId: $movieId")
            viewModel.loadDetailsMovie(movieId)
        }

        viewModel.detailsMovie.observe(this){
            movieDetails ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+movieDetails.posterPath)
                .into(binding.imgMovie)

            binding.movieName.text = movieDetails.originalTitle
            binding.movieLanguage.text = movieDetails.originalLanguage
            binding.movieOverview.text = movieDetails.overview
            binding.movieReleaseDate.text = movieDetails.releaseDate
            binding.movieRating.text = movieDetails.voteAverage.toString()
        }

    }
}