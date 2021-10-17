package com.example.movies.ui.main.profile_frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.movies.R
import com.example.movies.data.dataBase.UserDataBase
import com.example.movies.data.models.FavData
import com.example.movies.utils.DATABASE_FAV_TABLE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.toolbar.view.*

class FavoriteFragment : Fragment() {

    //Room Db
    private val dataBase: UserDataBase by lazy {
        Room.databaseBuilder(requireContext(), UserDataBase::class.java, DATABASE_FAV_TABLE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val favDataList: MutableList<FavData> = mutableListOf()
    private val recyclerFavoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter(requireContext(), favDataList)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationPage_includeToolbar.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_tv.text = "Favorite"
        requireActivity().navigationPage_includeToolbar.toolbar1_favorite_tv.visibility = View.GONE
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.visibility = View.VISIBLE
        checkList()
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    fun checkList(){
        if (dataBase.favDao().getAllFav().isNotEmpty() && dataBase.favDao().getAllFav().size > 0) {
            fav_frag_empty_const.visibility = View.GONE
            favorite_rv.visibility = View.VISIBLE
            favDataList.clear()
            favDataList.addAll(dataBase.favDao().getAllFav())
            favorite_rv.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
                adapter = recyclerFavoriteAdapter
            }
            recyclerFavoriteAdapter.notifyDataSetChanged()
        } else {
            fav_frag_empty_const.visibility = View.VISIBLE
            favorite_rv.visibility = View.GONE

        }
    }
}