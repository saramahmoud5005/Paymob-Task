package com.example.moviesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.MoviesResponse
import com.example.moviesapp.R
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.presentation.MovieDetailsActivity

class MoviesAdapter(
    private val context: Context,
): PagingDataAdapter<MoviesResponse.Movie, MoviesAdapter.ViewHolder>(diffUtil) {

    val sharedPreferences = context.getSharedPreferences("movies_sp", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    class ViewHolder(val binding:MovieItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            Glide.with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w500"+movie.posterPath)
                .into(holder.binding.movieImage)
        }

        if (movie != null) {
            holder.binding.movieName.text = movie.originalTitle
        }

        if (movie != null) {
            holder.binding.movieRating.text = movie.voteAverage.toString()
        }

        if (movie != null) {
            holder.binding.movieReleaseDate.text = movie.releaseDate
        }
        holder.binding.movieItem.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            if (movie != null) {
                intent.putExtra("movie_id",movie.id)
            }
            context.startActivity(intent)
        }

        holder.binding.favMovie.setOnClickListener{
            if (movie != null) {
                val isFavMovie = sharedPreferences.getBoolean("${movie.id}",false)
                editor.putBoolean("${movie.id}", !isFavMovie)
                editor.apply()
            }
        }
        if(movie!=null){
            val isFavMovie = sharedPreferences.getBoolean("${movie.id}",false)
            if(isFavMovie){
                holder.binding.favMovie.setImageResource(R.drawable.ic_favorite_filled)
            }else{
                holder.binding.favMovie.setImageResource(R.drawable.ic_favorite_border)
            }
        }

    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<MoviesResponse.Movie>() {
            override fun areItemsTheSame(oldItem: MoviesResponse.Movie, newItem: MoviesResponse.Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesResponse.Movie, newItem: MoviesResponse.Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}