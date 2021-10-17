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

class BestFragTrendingAdapter constructor(
    private val context: Context,
    private val moviesListModel: MutableList<MoviesListModel.Result>
) :
    RecyclerView.Adapter<BestFragTrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_fragment_best_trending, parent, false))
    }

    override fun onBindViewHolder(holder: BestFragTrendingAdapter.ViewHolder, position: Int) {

        holder.bindData(moviesListModel[position])
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bestTrendingFragTitleTxt =
            itemView.findViewById(R.id.row_best_trending_title_tv) as TextView
        private val bestTrendingFragPopularityTxt =
            itemView.findViewById(R.id.row_best_trending_rated_tv) as TextView
         private val bestTrendingFragPosterImg =
            itemView.findViewById(R.id.row_best_trending_posterImg) as ImageView

        fun bindData(moviesListModel: MoviesListModel.Result) {
            itemView.setOnClickListener {

                val bundle = Bundle()
                val id: Int = moviesListModel.id
                bundle.putInt(MOVIE_ID, id)

                it.findNavController()
                    .navigate(R.id.action_bestFragment_to_TrendingFragment, bundle)
            }
            bestTrendingFragTitleTxt.append(moviesListModel.title)
            bestTrendingFragPopularityTxt.append(moviesListModel.popularity.toString())

            val imgPath = "https://image.tmdb.org/t/p/original/" + moviesListModel.posterPath
            Glide.with(context)
                .load(imgPath)
                .into(bestTrendingFragPosterImg)
        }
    }
}