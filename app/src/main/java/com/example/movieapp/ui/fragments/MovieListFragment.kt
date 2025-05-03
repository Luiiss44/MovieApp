package com.example.movieapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.api.MovieApi
import com.example.movieapp.api.RetrofitClient
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.ui.adapters.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: MovieAdapter
    private val api = RetrofitClient.instance.create(MovieApi::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)

        adapter = MovieAdapter(
            movieList = emptyList(),
            onClick = { movie ->
                val action = MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(movie)
                findNavController().navigate(action)
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        loadPopularMovies()

        binding.btnFavorites.setOnClickListener {
            findNavController().navigate(
                MovieListFragmentDirections.actionMovieListFragmentToFavoritesFragment()
            )
        }

        return binding.root
    }

    private fun loadPopularMovies() {
        api.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponse ->
                        adapter.submitList(movieResponse.results)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                // Aquí podrías mostrar un mensaje de error al usuario si lo deseas
            }
        })
    }
}
