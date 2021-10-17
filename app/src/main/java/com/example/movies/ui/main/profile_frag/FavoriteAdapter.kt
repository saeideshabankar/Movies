package com.example.movies.ui.main.profile_frag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.dataBase.UserDataBase
import com.example.movies.data.models.FavData
import com.example.movies.utils.DATABASE_FAV_TABLE
import com.example.movies.utils.MOVIE_ID
import kotlinx.android.synthetic.main.fragment_details.*

class FavoriteAdapter(
    private val context: Context,
    private val favDataList: MutableList<FavData>
) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    //Room Db
    private val dataBase: UserDataBase by lazy {
        Room.databaseBuilder(context, UserDataBase::class.java, DATABASE_FAV_TABLE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_fav_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(favDataList[position])
    }

    override fun getItemCount(): Int {
        return favDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTv = itemView.findViewById(R.id.fav_result_title_txt) as TextView
        private val releaseTv = itemView.findViewById(R.id.fav_result_releaseData_txt) as TextView
        private val deleteBtn = itemView.findViewById(R.id.fav_delete_btn) as Button
        private val profileFav = itemView.findViewById(R.id.row_fav_adapter_profile_Img) as ImageView

        fun bindData(favData: FavData) {
            titleTv.text = favData.title
            releaseTv.text = favData.releaseData

            var imgPath = "https://image.tmdb.org/t/p/original/" +favData.adult

            Glide.with(context)
                .load(imgPath)
                .into(profileFav)

            deleteBtn.setOnClickListener {
                dataBase.favDao().deleteFavById(favData.movieId)
                favDataList.remove(favData)
                notifyDataSetChanged()
            }
            itemView.setOnClickListener {
                val bundle = Bundle()
                val id: Int? = favData.movieId
                bundle.putInt(MOVIE_ID, id!!)
                it.findNavController()
                    .navigate(R.id.action_favoriteFragment_to_detailsFragment, bundle)

            }
        }
    }
}