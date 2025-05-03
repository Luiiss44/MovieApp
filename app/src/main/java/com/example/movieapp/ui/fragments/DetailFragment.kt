package com.example.movieapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.model.Movie
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import androidx.navigation.fragment.navArgs

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val database = FirebaseDatabase.getInstance("https://movieapp-56d3f-default-rtdb.europe-west1.firebasedatabase.app").reference
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val movie = args.movie

        // Mostrar los datos
        binding.tvTitle.text = movie.title
        binding.tvOverview.text = movie.overview
        binding.tvReleaseDate.text = "Fecha de estreno: ${movie.release_date}"
        binding.tvRating.text = "Puntuación: ${movie.vote_average}"

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(binding.ivPoster)

        // Botón añadir a favoritos
        binding.btnAddFavorite.setOnClickListener {
            Log.d("Firebase", "Click en botón añadir a favoritos")
            Log.d("Firebase", "Guardando película: $movie")
            addMovieToFavorites(movie)
        }

        // Botón ver tráiler
        binding.btnWatchTrailer.setOnClickListener {
            val youtubeUrl = "https://www.youtube.com/results?search_query=${movie.title} trailer"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
            startActivity(intent)
        }

        return binding.root
    }

    private fun addMovieToFavorites(movie: Movie) {
        userId?.let {
            database.child("users").child(it).child("favorites")
                .child(movie.id.toString())
                .setValue(movie)
                .addOnSuccessListener {
                    Log.d("Firebase", "Película añadida a favoritos")
                    Toast.makeText(requireContext(), "Película añadida a favoritos", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Error al añadir a favoritos", e)
                    Toast.makeText(requireContext(), "Error al guardar", Toast.LENGTH_SHORT).show()
                }
        } ?: run {
            Log.e("Firebase", "Usuario no autenticado, no se puede guardar la película.")
            Toast.makeText(requireContext(), "Debes iniciar sesión para guardar favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}
