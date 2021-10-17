package com.example.movies.ui.main.trending_frag

import com.example.movies.data.models.MoviesListModel
import com.example.movies.ui.base_frag.BasePresenter
import com.example.movies.ui.base_frag.BaseView

interface TrendingContract {

    interface View : BaseView {
        fun loadTrendingApiList(data: MutableList<MoviesListModel.Result>)
    }

    interface Presenter : BasePresenter {
        fun callTrendingApiList(movieKind:String,movieDaily:String,api_key:String)
    }
}