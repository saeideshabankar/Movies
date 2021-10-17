package com.example.movies.ui.main.top_rated_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.models.MoviesListModel
import com.example.movies.utils.API_KEY
import com.example.movies.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_best_to_all.*
import kotlinx.android.synthetic.main.toolbar.view.*


class TopRatedMovieFragment : Fragment(), TopRatedMovieContract.View {

    private val topRatingMoviesListModels: MutableList<MoviesListModel.Result> = mutableListOf()
    private val recyclerTopRatingAdapter: RecyclerTopRatedAdapter by lazy {
        RecyclerTopRatedAdapter(requireContext(), topRatingMoviesListModels)
    }

    private var page: Int = 1
    private val presenter: TopRatingMoviePresenterImpl by lazy { TopRatingMoviePresenterImpl(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_to_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationPage_includeToolbar.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_tv.text = "Top Rated"
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_favorite_tv.visibility = View.GONE
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.setOnClickListener {
            requireActivity().onBackPressed()
        }
        presenter.callTopRatedMovieApiList(API_KEY, page)
    }

    override fun loadTopRatedMovieApiList(data: MutableList<MoviesListModel.Result>) {


        topRatingMoviesListModels.clear()
        topRatingMoviesListModels.addAll(data)

        best_to_all_Frag_rv.apply {

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recyclerTopRatingAdapter
        }

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
        best_to_all_frag_progress.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        best_to_all_frag_progress.visibility = View.GONE
    }
}