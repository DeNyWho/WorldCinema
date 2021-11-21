package com.example.worldcinema.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.R
import com.example.worldcinema.adapter.TagsAdapter
import com.example.worldcinema.mainViewModel.MainViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso

class MovieFragment : Fragment() {

    companion object{
        const val age = "age"
        const val description = "description"
        const val images = "images"
        const val movieId = "movieId"
        const val name = "name"
        const val poster = "poster"
        const val tags = "tags"
    }

    private lateinit var toolBar: MaterialToolbar
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        val bundle = arguments
        val age = bundle?.getString(age)
        val description = bundle?.getString(description)
        val images = bundle?.getString(images)
        val name = bundle?.getString(name)
        val poster = bundle?.getString(poster)
        val movieId = bundle?.getString(movieId)
        val tags = bundle!!.getStringArrayList(tags)
        val image = view.findViewById<ImageView>(R.id.image)
        val title = view.findViewById<TextView>(R.id.ToolBarText)
        val tagsRecyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        val descriptionTextView = view.findViewById<TextView>(R.id.description)
        val framesRecyclerView = view.findViewById<RecyclerView>(R.id.frames_recycler)
        val framesTitle = view.findViewById<TextView>(R.id.frames)
        val episodesRecyclerView = view.findViewById<RecyclerView>(R.id.episodes_recyclerView)

        toolBar.menu.findItem(R.id.comments).setIcon(R.drawable.comment_some)

        //tagsRecyclerView
//        tagsRecyclerView.adapter = tags?.let { TagsAdapter(it) }

        //descriptionTextView
        descriptionTextView.text = description

        //framesRecyclerView
        if (images != null) {
            if (images.isNotEmpty()) {
//                framesRecyclerView.adapter = FramesAdapter(images)
                framesTitle.isVisible = true
                framesRecyclerView.isVisible = true
            }
        }

        //EpisodesRecyclerView
//        episodesRecyclerView.adapter = EpisodesAdapter(List<EpisodeInfo>())

        title.text = name
        Picasso
            .get()
            .load("http://cinema.areas.su/up/images/$poster")
            .into(image)

        when (age) {
            "18" -> {
                toolBar.menu.findItem(R.id.age).setIcon(R.drawable.ic_18)
            }
            "16" -> {
                toolBar.menu.findItem(R.id.age).setIcon(R.drawable.ic_16)
            }
            "12" -> {
                toolBar.menu.findItem(R.id.age).setIcon(R.drawable.ic_12)
            }
            "6" -> {
                toolBar.menu.findItem(R.id.age).setIcon(R.drawable.ic_6)
            }
            "0" -> {
                toolBar.menu.findItem(R.id.age).setIcon(R.drawable.ic_0)
            }
        }

        toolBar.setNavigationIcon(R.drawable.arrow)
        toolBar.setNavigationIconTint(Color.WHITE)
        toolBar.setTitleTextColor(Color.WHITE)
        toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }

//    private fun downloadInfoEpisodesRequiredMovie(movieId: String) {
//        viewModel.downloadInfoEpisodesRequiredMovie(movieId)
//        lifecycleScope.launch(Dispatchers.Main) {
//            viewModel.episodeInfo.collect { listEpisodesInfo ->
//                if (listEpisodesInfo != null && listEpisodesInfo.isNotEmpty()) {
//                    updateEpisodesRecycler(listEpisodesInfo)
//                    episodesTextView.isVisible = true
//                    episodesRecyclerView.isVisible = true
//                    setVideoView(listEpisodesInfo[0].preview)
//                    cf.log(TAG, "downloadInfoEpisodesRequiredMovie | " +
//                            "listEpisodesInfo=$listEpisodesInfo")
//                } else {
//                    cf.log(TAG, "downloadInfoEpisodesRequiredMovie | " +
//                            "listEpisodesInfo = null")
//                }
//            }
//        }
//    }

}