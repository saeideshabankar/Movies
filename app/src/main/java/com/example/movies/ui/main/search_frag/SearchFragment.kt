package com.example.movies.ui.main.search_frag

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.data.models.MoviesListModel
import com.example.movies.utils.API_KEY
import com.example.movies.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.toolbar.view.*

class SearchFragment : Fragment(), SearchContract.View {

    private var searchTxt = ""
    private val searchMoviesListModels: MutableList<MoviesListModel.Result> = mutableListOf()

    private val recyclerSearchAdapter: RecyclerSearchAdapter by lazy {
        RecyclerSearchAdapter(requireContext(), searchMoviesListModels)
    }

    private val presenter: SearchPresenterImpl by lazy { SearchPresenterImpl(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationPage_includeToolbar.visibility = View.GONE

        val charset = ('a'..'z')

        presenter.callSearchApiList(API_KEY, charset.random().toString())
        search_frag_searchEdt.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search_frag_empty_const.visibility = View.GONE
                search_frag_rv.visibility = View.VISIBLE
                searchTxt = s.toString()

                if (search_frag_searchEdt.text.isNotEmpty()) {
                    presenter.callSearchApiList(API_KEY, searchTxt)
                } else {
                    presenter.callSearchApiList(API_KEY, charset.random().toString())
                }

            }

        })

    }

    override fun loadSearchApiList(data: MutableList<MoviesListModel.Result>) {

        if (data.isEmpty()) {
            search_frag_empty_const.visibility = View.VISIBLE
            search_frag_resultOfSearch.text = "The movie is not available"
            search_frag_rv.visibility = View.GONE
        }
        searchMoviesListModels.clear()
        searchMoviesListModels.addAll(data)

        search_frag_rv.apply {

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recyclerSearchAdapter
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
        search_frag_progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        search_frag_progressBar.visibility = View.GONE
    }
}