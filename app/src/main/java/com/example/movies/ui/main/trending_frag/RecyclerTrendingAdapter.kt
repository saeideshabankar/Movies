package com.example.movies.ui.main.trending_frag

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.movies.R
import com.example.movies.data.models.MoviesListModel
import com.example.movies.utils.MOVIE_ID

class RecyclerTrendingAdapter constructor(
    private val context: Context,
    private val moviesListModel: MutableList<MoviesListModel.Result>
) :
    RecyclerView.Adapter<RecyclerTrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_best_to_all, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerTrendingAdapter.ViewHolder, position: Int) {

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
        private val bestToAllProgress=
            itemView.findViewById(R.id.row_best_to_all_progress) as ProgressBar

        fun bindData(moviesListModel: MoviesListModel.Result) {

            bestToAllTitleTxt.text=moviesListModel.title
            bestToAllPopularityTxt.text=moviesListModel.popularity.toString()

            val imgPath = "https://image.tmdb.org/t/p/original/" + moviesListModel.posterPath
            Glide.with(context)
                .load(imgPath)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                    ): Boolean {
                        bestToAllProgress.visibility = View.GONE
                        return false
                    }
                    override fun onResourceReady(
                        resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        bestToAllProgress.visibility = View.GONE
                        return false
                    }
                })
                .into(bestToAllPosterImg)

            bestToAllDetailsBtn.setOnClickListener {

                val bundle = Bundle()
                val id: Int = moviesListModel.id
                bundle.putInt(MOVIE_ID, id)

                it.findNavController()
                    .navigate(R.id.action_TrendingFragment_to_detailsFragment, bundle)

            }
        }
    }
}