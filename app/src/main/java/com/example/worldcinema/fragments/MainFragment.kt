package com.example.worldcinema.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.R
import com.example.worldcinema.adapter.MyAdapter
import com.example.worldcinema.data.MovieList
import com.example.worldcinema.mainViewModel.MainViewModel
import com.example.worldcinema.retrofit.NetworkService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

//    private val myAdapter by lazy{MyAdapter(it) }
    private var filter = "forMe"
    private lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val trend = view.findViewById<FrameLayout>(R.id.trend)
        val new = view.findViewById<FrameLayout>(R.id.New)
        val forYou = view.findViewById<FrameLayout>(R.id.For_you)
        recyclerView = view.findViewById(R.id.recycler)
        val under_trend = view.findViewById<View>(R.id.under_trend)
        under_trend.visibility = View.VISIBLE

        trend.setOnClickListener {
            filter = "inTrend"
            under_trend.visibility = View.VISIBLE
            under_new.visibility = View.INVISIBLE
            under_you.visibility = View.INVISIBLE
        }

        new.setOnClickListener {
            filter = "new"
            under_trend.visibility = View.INVISIBLE
            under_you.visibility = View.INVISIBLE
            under_new.visibility = View.VISIBLE
        }

        forYou.setOnClickListener {
            filter = "forMe"
            under_trend.visibility = View.INVISIBLE
            under_you.visibility = View.VISIBLE
            under_new.visibility = View.INVISIBLE
        }

        val image = view.findViewById<ImageView>(R.id.image_1)
        val image_background = view.findViewById<ImageView>(R.id.image_2)

        CoroutineScope(Dispatchers.IO).launch {

            val response = NetworkService.api().getCover()
            withContext(Dispatchers.Main){
                val backgroundImage = response.body()?.backgroundImage
                val foregroundImage = response.body()?.foregroundImage
                Picasso
                    .get()
                    .load("http://cinema.areas.su/up/images/$foregroundImage")
                    .into(image)
                Picasso
                    .get()
                    .load("http://cinema.areas.su/up/images/$backgroundImage")
                    .into(image_background)
            }
        }

        initViewModel(filter)
        return view
    }

    private fun initRecyclerView(data: MovieList){

        recyclerView.apply {
            val decoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
            addItemDecoration(decoration)
            recyclerAdapter = MyAdapter(data)
            adapter = recyclerAdapter
        }
    }

    private fun initViewModel(filter: String) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getMovieList().observe(viewLifecycleOwner,{
            if(it != null){
                initRecyclerView(it)
            } else {

            }
        })
        viewModel.getMovieData(filter)
    }
}