package com.example.movies.ui.main.popular_frag

import com.example.movies.data.models.MoviesListModel
import com.example.movies.ui.base_frag.BasePresenter
import com.example.movies.ui.base_frag.BaseView

interface PopularContract {
    interface View : BaseView {
        fun loadPopularApiList(data: MutableList<MoviesListModel.Result>)
    }

    interface Presenter : BasePresenter {
        fun callPopularApiList(api_key: String, page: Int)
    }
}