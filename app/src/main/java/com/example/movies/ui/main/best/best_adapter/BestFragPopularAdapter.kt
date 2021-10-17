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
import com.example.movies.utils.EventSendId
import com.example.movies.utils.MOVIE_ID
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class BestFragPopularAdapter constructor(
    private val context: Context,
    private val moviesListModel: MutableList<MoviesListModel.Result>
) :
    RecyclerView.Adapter<BestFragPopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_fragment_best_popular, parent, false))
    }

    override fun onBindViewHolder(holder: BestFragPopularAdapter.ViewHolder, position: Int) {

        holder.bindData(moviesListModel[position])
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bestPopularFragTitleTxt =
            itemView.findViewById(R.id.row_best_popular_title_tv) as TextView
        private val bestPopularFragPopularityTxt =
            itemView.findViewById(R.id.row_best_popular_rated_tv) as TextView
         private val bestPopularFragPosterImg =
            itemView.findViewById(R.id.row_best_popular_posterImg) as ImageView

        fun bindData(moviesListModel: MoviesListModel.Result) {

            val bundle = Bundle()
            val id: Int = moviesListModel.id
            bundle.putInt(MOVIE_ID, id)
            EventBus.getDefault().post(EventSendId.OnSendMovieId(id))

            itemView.setOnClickListener {

                it.findNavController()
                    .navigate(R.id.action_BestFragment_to_detailsFragment, bundle)
            }
             bestPopularFragTitleTxt.append(moviesListModel.title)
             bestPopularFragPopularityTxt.append(moviesListModel.popularity.toString())

            val imgPath = "https://image.tmdb.org/t/p/original/" + moviesListModel.posterPath
            Glide.with(context)
                .load(imgPath)
                .into(bestPopularFragPosterImg)
        }

    }


}