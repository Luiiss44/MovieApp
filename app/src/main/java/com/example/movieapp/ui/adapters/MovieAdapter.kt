package com.example.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.Movie

class MovieAdapter(
    private var movieList: List<Movie>,
    private val onClick: (Movie) -> Unit,
    private val showDeleteButton: Boolean = false,
    private val onDeleteClick: ((Movie) -> Unit)? = null
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvOverview: TextView = view.findViewById(R.id.tvOverview)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.tvTitle.text = movie.title
        holder.tvOverview.text = movie.overview
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(holder.imgPoster)

        holder.itemView.setOnClickListener {
            onClick(movie)
        }

        if (showDeleteButton && onDeleteClick != null) {
            holder.btnDelete.visibility = View.VISIBLE
            holder.btnDelete.setOnClickListener {
                onDeleteClick.invoke(movie)
            }
        } else {
            holder.btnDelete.visibility = View.GONE
        }
    }

    fun submitList(newList: List<Movie>) {
        movieList = newList
        notifyDataSetChanged()
    }
}