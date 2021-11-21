package com.example.worldcinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.R

class TagsAdapter(private val tagsList: Array<String>): RecyclerView.Adapter<TagsAdapter.TagsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_tags, parent, false)
        return TagsHolder(view)
    }

    override fun onBindViewHolder(holder: TagsHolder, position: Int) {
        val tag = tagsList?.get(position)
        holder.bind(tag)
    }

    override fun getItemCount(): Int {
        return tagsList?.size ?: 1
    }

    class TagsHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val tagText = itemView.findViewById<TextView>(R.id.tag_textView)

        fun bind(tag: String?){
            tagText.text = tag
        }
    }
}