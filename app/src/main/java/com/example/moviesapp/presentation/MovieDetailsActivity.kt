package com.example.moviesapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding
import com.example.moviesapp.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity :AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieDetails = viewModel.detailsMovie?.value

        if(movieDetails != null){
            Glide.with(this)
                .load(movieDetails.posterPath)
                .into(binding.imgMovie)

            binding.movieName.text = movieDetails.originalTitle
            binding.movieLanguage.text = movieDetails.originalLanguage
            binding.movieOverview.text = movieDetails.overview
            binding.movieReleaseDate.text = movieDetails.releaseDate
            binding.movieRating.text = movieDetails.voteAverage.toString()
        }



    }
}