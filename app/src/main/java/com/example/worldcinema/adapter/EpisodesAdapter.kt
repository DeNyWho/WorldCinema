package com.example.worldcinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.R
import com.example.worldcinema.data.EpisodeInfo
import com.squareup.picasso.Picasso

class EpisodesAdapter(private val episodes: List<EpisodeInfo>):
    RecyclerView.Adapter<EpisodesAdapter.EpisodesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_episodes, parent, false)
        return EpisodesHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodesHolder, position: Int) {
        val episodeInfo = episodes[position]
        holder.bind(episodeInfo, position)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    class EpisodesHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val imageView = itemView.findViewById<ImageView>(R.id.episode_imageView)
        private val titleTextView = itemView.findViewById<TextView>(R.id.episode_title)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.description_textView)
        private val yearTextView = itemView.findViewById<TextView>(R.id.year_textView)

        fun bind(episodeInfo: EpisodeInfo, position: Int){

            val episodeImageURL = if(episodeInfo.images.size > position) episodeInfo.images[position]
            else
                ""
            Picasso
                .get()
                .load("http://cinema.areas.su/up/images/$episodeImageURL")
                .into(imageView)

            titleTextView.text = episodeInfo.name
            descriptionTextView.text = episodeInfo.description
            yearTextView.text = episodeInfo.year
        }
    }
}