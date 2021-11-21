package com.example.worldcinema.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.R
import com.example.worldcinema.data.MovieList
import com.example.worldcinema.data.MoviesListItem
import com.example.worldcinema.fragments.MovieFragment
import com.squareup.picasso.Picasso


class MyAdapter(private var moviesListItem: MovieList) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var movieList = moviesListItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val image = view.findViewById<ImageView>(R.id.image_preview)

        fun bind(movie: MoviesListItem) {

            Picasso
                .get()
                .load("http://cinema.areas.su/up/images/"+ movie.poster)
                .into(image)
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener {
            val temp = movieList[position]
            val bundle = Bundle()
            val yfc = MovieFragment()
            bundle.putString("name", temp.name)
            bundle.putString("age", temp.age)
            bundle.putString("description", temp.description)
            bundle.putStringArrayList("images", temp.images)
            bundle.putString("movieId", temp.movieId)
            bundle.putString("poster", temp.poster)
            bundle.putString("tags", temp.tags.toString())
            bundle.putStringArrayList("tags", temp.tags)
            yfc.arguments = bundle
            yfc.findNavController().navigate(R.id.action_mainFragment_to_movieFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}