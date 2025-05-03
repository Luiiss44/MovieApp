package com.example.movieapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentFavoritesBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.adapters.MovieAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: MovieAdapter
    private val database = FirebaseDatabase.getInstance("https://movieapp-56d3f-default-rtdb.europe-west1.firebasedatabase.app").reference
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        adapter = MovieAdapter(
            movieList = emptyList(),
            onClick = {},
            showDeleteButton = true,
            onDeleteClick = { movie ->
                userId?.let {
                    database.child("users").child(it).child("favorites")
                        .child(movie.id.toString())
                        .removeValue()
                        .addOnSuccessListener {
                            Log.d("Firebase", "Película eliminada")
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firebase", "Error al eliminar", e)
                        }
                }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        loadFavorites()

        return binding.root
    }

    private fun loadFavorites() {
        userId?.let {
            val ref = database.child("users").child(it).child("favorites")
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val favorites = mutableListOf<Movie>()
                    for (child in snapshot.children) {
                        child.getValue(Movie::class.java)?.let { favorites.add(it) }
                    }
                    adapter.submitList(favorites)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error al cargar favoritos", error.toException())
                }
            })
        }
    }
}
