package com.example.movies.ui.main.popular_frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.data.models.MoviesListModel
import com.example.movies.ui.main.top_rated_fragment.RecyclerTopRatedAdapter
import com.example.movies.utils.API_KEY
import com.example.movies.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_best_to_all.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.greenrobot.eventbus.EventBus


class PopularFragment : Fragment(), PopularContract.View {

    private val listModels: MutableList<MoviesListModel.Result> = mutableListOf()
    private val recyclerPopularAdapter: RecyclerPopularAdapter by lazy {
        RecyclerPopularAdapter(requireContext(), listModels)
    }

    private val presenter: PopularPresenterImpl by lazy { PopularPresenterImpl(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_to_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationPage_includeToolbar.toolbar1_tv.text = "Popular"

        requireActivity().navigationPage_includeToolbar.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_favorite_tv.visibility = View.GONE
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.setOnClickListener {
            requireActivity().onBackPressed()
        }
        presenter.callPopularApiList(API_KEY, 1)

    }

    override fun loadPopularApiList(data: MutableList<MoviesListModel.Result>) {

        listModels.clear()
        listModels.addAll(data)
        best_to_all_Frag_rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recyclerPopularAdapter
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