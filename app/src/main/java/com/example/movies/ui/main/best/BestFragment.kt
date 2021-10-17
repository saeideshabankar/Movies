package com.example.movies.ui.main.best

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.data.models.MoviesListModel
import com.example.movies.ui.main.best.best_adapter.BestFragPopularAdapter
import com.example.movies.ui.main.best.best_adapter.BestFragTopRatedAdapter
import com.example.movies.ui.main.best.best_adapter.BestFragTrendingAdapter
import com.example.movies.utils.API_KEY
import com.example.movies.utils.EventSendId
import com.example.movies.utils.MOVIE_ID
import com.example.movies.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_best.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class
BestFragment : Fragment(), BestContract.View {
    private val bundle1 = Bundle()

    //Popular
    private val popularListModels: MutableList<MoviesListModel.Result> = mutableListOf()
    private val recyclerPopularAdapter: BestFragPopularAdapter by lazy {
        BestFragPopularAdapter(requireContext(), popularListModels)
    }

    //Top Rated
    private val topRatingMoviesListModels: MutableList<MoviesListModel.Result> = mutableListOf()
    private val recyclerTopRatingAdapter: BestFragTopRatedAdapter by lazy {
        BestFragTopRatedAdapter(requireContext(), topRatingMoviesListModels)
    }

    //Trending
    private val trendingMoviesListModels: MutableList<MoviesListModel.Result> = mutableListOf()
    private val recyclerTrendingAdapter: BestFragTrendingAdapter by lazy {
        BestFragTrendingAdapter(requireContext(), trendingMoviesListModels)
    }


    //Presenter
    private val presenter: BestPresenterImpl by lazy { BestPresenterImpl(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Toolbar

        requireActivity().navigationPage_includeToolbar.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_favorite_tv.visibility = View.GONE
        requireActivity().navigationPage_includeToolbar.toolbar1_tv.text = "Movies"
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.visibility = View.GONE

        //Call apis
        presenter.popularWithoutPageMoviesListApi(API_KEY)
        presenter.topRatedWithoutPageMoviesListApi(API_KEY)
        presenter.callTrendingApiList("movie", "week", API_KEY)

        bestFrag_popular_viewMore_tv.setOnClickListener {

            onReceiveMovieId(EventSendId.OnSendMovieId(id))
            it.findNavController()
                .navigate(R.id.action_bestFragment_to_popularFragment, bundle1)

        }
        bestFrag_trending_tv.setOnClickListener {

            onReceiveMovieId(EventSendId.OnSendMovieId(id))
            it.findNavController()
                .navigate(R.id.action_bestFragment_to_TrendingFragment, bundle1)

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceiveMovieId(event: EventSendId.OnSendMovieId) {

        val id: Int = event.movieId
        bundle1.putInt(MOVIE_ID, id)
    }

    override fun loadPopularApiList(data: MutableList<MoviesListModel.Result>) {
        popularListModels.clear()
        popularListModels.addAll(data)
        bestFrag_popular_rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerPopularAdapter
        }
    }

    override fun loadTopRatedMovieApiList(data: MutableList<MoviesListModel.Result>) {
        topRatingMoviesListModels.clear()
        topRatingMoviesListModels.addAll(data)

        bestFrag_topRated_rv.apply {

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerTopRatingAdapter
        }

    }

    override fun loadTrendingApiList(data: MutableList<MoviesListModel.Result>) {
        trendingMoviesListModels.clear()
        trendingMoviesListModels.addAll(data)

        bestFrag_trending_rv.apply {

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerTrendingAdapter
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
        Toast.makeText(requireContext(), "This App is not available in your country ", Toast.LENGTH_SHORT).show()

    }

    override fun serverError(error: String) {

        Toast.makeText(requireContext(), "Server has error ", Toast.LENGTH_SHORT).show()
    }

    override fun showLoader() {

        bestFrag_progress.visibility = View.VISIBLE
    }

    override fun hideLoader() {

        bestFrag_progress.visibility = View.GONE
    }
}