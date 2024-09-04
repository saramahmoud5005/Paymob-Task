package com.example.moviesapp.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviesapp.R
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

        val sharedPreferences = this.getSharedPreferences("movies_sp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

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

            val isFavMovie = sharedPreferences.getBoolean("${movieDetails.id}",false)
            if(isFavMovie){
                binding.favouriteBtn.setImageResource(R.drawable.ic_favorite_filled)
            }else{
                binding.favouriteBtn.setImageResource(R.drawable.ic_favorite_border)
            }

            binding.favouriteBtn.setOnClickListener{
                val isFavMovie = sharedPreferences.getBoolean("${movieDetails.id}",false)
                editor.putBoolean("${movieDetails.id}", !isFavMovie)
                editor.apply()
            }
        }

    }
}