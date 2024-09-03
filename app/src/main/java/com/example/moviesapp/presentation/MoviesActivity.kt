package com.example.moviesapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.adapters.MoviesAdapter
import com.example.moviesapp.databinding.ActivityMoviesBinding
import com.example.moviesapp.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    private val movieViewModel : MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesAdapter = MoviesAdapter()
        setUpMoviesRecyclerView()


        lifecycleScope.launch(Dispatchers.IO) {
            movieViewModel.movies.collect {
//                Log.d("TAG1000", "onCreate: $it")
                withContext(Dispatchers.Main){
                    moviesAdapter.submitData(it)
                }
            }
        }
    }

    private fun setUpMoviesRecyclerView(){
        binding.moviesRv.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
        binding.moviesRv.adapter = moviesAdapter
    }
}