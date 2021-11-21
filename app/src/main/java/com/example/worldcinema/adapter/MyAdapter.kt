package com.example.worldcinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.R
import com.example.worldcinema.data.MovieList
import com.example.worldcinema.data.MoviesListItem
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
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}