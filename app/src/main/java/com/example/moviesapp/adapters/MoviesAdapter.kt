package com.example.moviesapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.MoviesResponse
import com.example.moviesapp.databinding.MovieItemBinding

class MoviesAdapter: PagingDataAdapter<MoviesResponse.Movie, MoviesAdapter.ViewHolder>(diffUtil) {

//    private val movies : List<MoviesResponse.Movie> = emptyList()
    class ViewHolder(val binding:MovieItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = differ.currentList[position]
        Glide.with(holder.itemView)
                .load(movie.posterPath)
            .into(holder.binding.movieImage)

        holder.binding.movieName.text = movie.originalTitle

        holder.binding.movieRating.text = movie.voteAverage.toString()

        holder.binding.movieReleaseDate.text = movie.releaseDate
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<MoviesResponse.Movie>() {
            override fun areItemsTheSame(oldItem: MoviesResponse.Movie, newItem: MoviesResponse.Movie): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesResponse.Movie, newItem: MoviesResponse.Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val differ = AsyncListDiffer(this,diffUtil)
}



//class MoviesAdapter :
//    PagingDataAdapter<MoviesResponse.Movie, MoviesAdapter.ViewHolder>(differCallback) {
//
//    private lateinit var binding: MovieItemBinding
//    private lateinit var context: Context
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        binding = MovieItemBinding.inflate(inflater, parent, false)
//        context = parent.context
//        return ViewHolder()
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getItem(position)!!)
//        holder.setIsRecyclable(false)
//    }
//
//    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
//
//        @SuppressLint("SetTextI18n")
//        fun bind(item: MoviesResponse.Movie) {
//            binding.apply {
//                movieName.text = item.title
//                movieReleaseDate.text = item.releaseDate
//                movieRating.text=item.voteAverage.toString()
//
//                Glide.with(itemView)
//                .load(item?.posterPath)
//                .into(movieImage)
//
//                root.setOnClickListener {
//                    onItemClickListener?.let {
//                        it(item)
//                    }
//                }
//            }
//        }
//    }
//
//    private var onItemClickListener: ((MoviesResponse.Movie) -> Unit)? = null
//
//    fun setOnItemClickListener(listener: (MoviesResponse.Movie) -> Unit) {
//        onItemClickListener = listener
//    }
//
//    companion object {
//        val differCallback = object : DiffUtil.ItemCallback<MoviesResponse.Movie>() {
//            override fun areItemsTheSame(oldItem: MoviesResponse.Movie, newItem: MoviesResponse.Movie): Boolean {
//                return oldItem.id == oldItem.id
//            }
//
//            override fun areContentsTheSame(oldItem: MoviesResponse.Movie, newItem: MoviesResponse.Movie): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//}