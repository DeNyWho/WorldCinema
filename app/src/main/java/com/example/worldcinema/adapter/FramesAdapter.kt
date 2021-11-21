package com.example.worldcinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.R
import com.squareup.picasso.Picasso

class FramesAdapter(private val frameList: List<String>): RecyclerView.Adapter<FramesAdapter.FramesHolder>() {
    override fun getItemCount(): Int {
        return frameList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FramesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_frames, parent, false)
        return FramesHolder(view)
    }

    override fun onBindViewHolder(holder: FramesHolder, position: Int) {
        val frameURL = frameList[position]
        holder.bind(frameURL)
    }

    class FramesHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val frameImageView: ImageView = itemView.findViewById(R.id.frame_imageView)

        fun bind(frameURL: String){

            Picasso
                .get()
                .load("http://cinema.areas.su/up/images/$frameURL")
                .into(frameImageView)
        }
    }
}