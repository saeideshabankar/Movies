package com.example.movies.ui.main.search_frag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.models.MoviesListModel
import com.example.movies.utils.MOVIE_ID

class RecyclerSearchAdapter constructor(
    private val context: Context,
    private val moviesListModel: MutableList<MoviesListModel.Result>
) :
    RecyclerView.Adapter<RecyclerSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_best_to_all, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerSearchAdapter.ViewHolder, position: Int) {

        holder.bindData(moviesListModel[position])
    }

    override fun getItemCount(): Int {
        return moviesListModel.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bestToAllTitleTxt =
            itemView.findViewById(R.id.row_best_to_all_title_tv) as TextView

        private val bestToAllPopularityTxt =
            itemView.findViewById(R.id.row_best_to_all_rated_tv) as TextView
        private val bestToAllPosterImg =
            itemView.findViewById(R.id.row_best_to_all_posterImg) as ImageView
        private val bestToAllDetailsBtn =
            itemView.findViewById(R.id.row_best_to_all_detailsBtn) as Button
        fun bindData(moviesListModel: MoviesListModel.Result) {

            bestToAllTitleTxt.text=moviesListModel.title
            bestToAllPopularityTxt.text=moviesListModel.popularity.toString()

            val imgPath = "https://image.tmdb.org/t/p/original/" + moviesListModel.posterPath
            Glide.with(context)
                .load(imgPath)
                .into(bestToAllPosterImg)
            bestToAllDetailsBtn.setOnClickListener {

                val bundle = Bundle()
                val id: Int = moviesListModel.id
                bundle.putInt(MOVIE_ID, id)

                it.findNavController()
                    .navigate(R.id.action_SearchFragment_to_detailsFragment, bundle)

            }
        }
    }
}