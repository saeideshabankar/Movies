package com.example.movies.ui.main.details_frag.details_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.models.DetailsListModel

class RecyclerGenresAdapter(
    private val context: Context,
    private val genresMoviesList: MutableList<DetailsListModel.Genre>
) :
    RecyclerView.Adapter<RecyclerGenresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_details_frag_genres, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(genresMoviesList[position])
    }

    override fun getItemCount(): Int {
        return genresMoviesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genresTv = itemView.findViewById(R.id.genresTv) as TextView

        fun bindData(genre: DetailsListModel.Genre) {
            genresTv.text = genre.name
        }
    }
}