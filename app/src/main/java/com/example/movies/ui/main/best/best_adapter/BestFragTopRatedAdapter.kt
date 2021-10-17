package com.example.movies.ui.main.best.best_adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.models.MoviesListModel
import com.example.movies.utils.MOVIE_ID

class BestFragTopRatedAdapter constructor(
    private val context: Context,
    private val moviesListModel: MutableList<MoviesListModel.Result>
) :
    RecyclerView.Adapter<BestFragTopRatedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_frag_best_top_rated_movie, parent, false))
    }

    override fun onBindViewHolder(holder: BestFragTopRatedAdapter.ViewHolder, position: Int) {

        holder.bindData(moviesListModel[position])
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val popularFragTitleTxt =
            itemView.findViewById(R.id.topRate_frag_title_txt) as TextView
       private val popularFragPopularityTxt =
            itemView.findViewById(R.id.topRate_frag_popularity_txt) as TextView
          private val popularFragPosterImg =
            itemView.findViewById(R.id.bestTopRate_frag_poster_img) as ImageView

        fun bindData(moviesListModel: MoviesListModel.Result) {
            itemView.setOnClickListener {

                val bundle = Bundle()
                val id: Int = moviesListModel.id
                bundle.putInt(MOVIE_ID, id)

                it.findNavController()
                    .navigate(R.id.action_bestFragment_to_topRatedMovieFragment, bundle)
            }
            popularFragTitleTxt.append(moviesListModel.title)
            popularFragPopularityTxt.append(moviesListModel.popularity.toString())

            val imgPath = "https://image.tmdb.org/t/p/original/" + moviesListModel.posterPath
            Glide.with(context)
                .load(imgPath)
                .into(popularFragPosterImg)
        }
    }
}