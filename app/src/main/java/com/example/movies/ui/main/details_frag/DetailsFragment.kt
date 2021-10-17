package com.example.movies.ui.main.details_frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.dataBase.UserDataBase
import com.example.movies.data.models.DetailsListModel
import com.example.movies.data.models.FavData
import com.example.movies.data.models.TrailerModel
import com.example.movies.ui.main.details_frag.details_adapter.RecyclerGenresAdapter
import com.example.movies.ui.main.details_frag.details_adapter.RecyclerTrailerTextAdapter
import com.example.movies.utils.API_KEY
import com.example.movies.utils.DATABASE_FAV_TABLE
import com.example.movies.utils.MOVIE_ID
import com.example.movies.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.text.NumberFormat
import java.util.*


class DetailsFragment : Fragment(), DetailsContract.View {

    private lateinit var favData: FavData
    private lateinit var title: String
    private lateinit var posterPath: String
    private lateinit var language: String
    private lateinit var releaseDate: String
    private var budget: Int = 0
    private lateinit var overView: String
    private lateinit var popularity: String
    private lateinit var adult: String
    private var movieId: Int = 0

    //Room Db
    private val dataBaseFav: UserDataBase by lazy {
        Room.databaseBuilder(requireContext(), UserDataBase::class.java, DATABASE_FAV_TABLE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private val getAllFavList: MutableList<FavData> = mutableListOf()

    private val moviesDetailsImgListModels: MutableList<Int> = mutableListOf()
//    private val recyclerDetailsImgAdapter: RecyclerDetailsImgAdapter by lazy {
//        RecyclerDetailsImgAdapter(requireContext(), moviesDetailsImgListModels)
//    }

    private var moviesDetailsGenresListModels: MutableList<DetailsListModel.Genre> = mutableListOf()
    private val recyclerDetailsGenresAdapter: RecyclerGenresAdapter by lazy {
        RecyclerGenresAdapter(requireContext(), moviesDetailsGenresListModels)
    }
    private val moviesDetailsTrailerListModel: MutableList<TrailerModel.Result> = mutableListOf()
    private val recyclerTrailerTextAdapter: RecyclerTrailerTextAdapter by lazy {
        RecyclerTrailerTextAdapter(requireContext(), moviesDetailsTrailerListModel)
    }

    private val presenter: DetailsPresenterImpl by lazy { DetailsPresenterImpl(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationPage_includeToolbar.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_tv.text = "Details"
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_favorite_tv.visibility = View.GONE
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.setOnClickListener {
//            childFragmentManager.popBackStack()
            requireActivity().onBackPressed()
        }

        val movieId = arguments?.getInt(MOVIE_ID)
        if (movieId != null) {
            presenter.callDetailsApiList(movieId, API_KEY)
            //presenter.callImagesListApiList(movieId, API_KEY)
            presenter.callTrailerListApiList(movieId, API_KEY)
        } else {
            Toast.makeText(requireContext(), "id not available", Toast.LENGTH_SHORT).show()
        }

        getAllFavList.clear()
        getAllFavList.addAll(dataBaseFav.favDao().getAllFav())

        for (item in getAllFavList) {
            if (item.movieId == movieId) {
                details_page_savedFav_img.setImageResource(R.drawable.ic_bookmark_black)
                break
            } else {
                details_page_savedFav_img.setImageResource(R.drawable.ic_bookmark_white)
            }
        }
        details_page_savedFav_img.setOnClickListener {
            val favEmptyOrNot = dataBaseFav.favDao().checkFavById(movieId)

            if (favEmptyOrNot != null) {
                details_page_savedFav_img.setImageResource(R.drawable.ic_bookmark_white)
                dataBaseFav.favDao().deleteFavById(movieId)
            } else {
                favData =
                    FavData(movieId, title, posterPath, language, overView, releaseDate, popularity)
                details_page_savedFav_img.setImageResource(R.drawable.ic_bookmark_black)
                dataBaseFav.favDao().insertFav(favData)
            }
        }
        /*  moviesDetailsImgListModels.clear()
          addImgList()
          details_page_bigImg_rv.apply {

              layoutManager =
                  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
              adapter = recyclerDetailsImgAdapter
          }*/
    }

    override fun loadDetailsApiList(data: DetailsListModel) {
        title = data.title
        language = data.originalLanguage
        releaseDate = data.releaseDate
        budget = data.budget
        posterPath = data.posterPath.toString()
        val budgetStr = NumberFormat.getNumberInstance(Locale.US).format(budget)
        popularity = data.popularity.toString()
        overView = data.overview
        movieId = data.id
        adult = data.adult.toString()
        details_page_title_txt.text = title
        details_page_result_lang_txt.text = language
        details_page_release_tv.text = releaseDate
        details_page_budget_result_txt.text = budgetStr
        details_page_popularity_result_txt.text = popularity
        details_page_about_txt.text = overView
        var tvMaxLine = false
        details_page_about_txt.setOnClickListener {
            if (tvMaxLine) {

                details_page_about_txt.maxLines = 2
                tvMaxLine = false
            } else {
                details_page_about_txt.maxLines = 10
                tvMaxLine = true
            }
        }


        var imgPath = "https://image.tmdb.org/t/p/original/" + data.posterPath
        Glide.with(this)
            .load(imgPath)
            .into(details_page_postImg)
        details_page_adult_result_txt.text = data.adult.toString()
        moviesDetailsGenresListModels.clear()
        moviesDetailsGenresListModels.addAll(data.genres)

        details_page_genres_rv.apply {

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerDetailsGenresAdapter
        }
    }

    /*      override fun loadImagesApiList(data: MutableList<ImagesModel.Poster>) {
    moviesDetailsImgListModels.clear()
           addImgList()

     details_page_bigImg_rv.apply {

         layoutManager =
             LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
         adapter = recyclerDetailsImgAdapter
     }
       }*/

    override fun loadTrailerApiList(data: MutableList<TrailerModel.Result>) {

        moviesDetailsTrailerListModel.clear()
        moviesDetailsTrailerListModel.addAll(data)
        details_page_trailer_rv.apply {

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerTrailerTextAdapter
        }
    }

    private fun addImgList() {

        moviesDetailsImgListModels.add(R.drawable.spin_arena_small4)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small5)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small2)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small4)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small4)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small5)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small2)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small4)
        moviesDetailsImgListModels.add(R.drawable.spin_arena_small5)
    }

    override fun checkNetWorkConnection(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun errorNetWorkConnection() {

        Toast.makeText(requireContext(), "Error network connection", Toast.LENGTH_SHORT).show()
    }

    override fun responseCodeError() {

    }

    override fun responseError(error: Throwable) {

    }

    override fun serverError(error: String) {

        Toast.makeText(requireContext(), "Server has error ", Toast.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        details_progress.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        details_progress.visibility = View.GONE
    }
}

