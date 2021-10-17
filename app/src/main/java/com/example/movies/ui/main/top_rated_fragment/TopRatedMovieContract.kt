package com.example.movies.ui.main.top_rated_fragment

import com.example.movies.data.models.MoviesListModel
import com.example.movies.ui.base_frag.BasePresenter
import com.example.movies.ui.base_frag.BaseView

interface TopRatedMovieContract {

    interface View : BaseView {
        fun loadTopRatedMovieApiList(data: MutableList<MoviesListModel.Result>)
    }

    interface Presenter : BasePresenter {
        fun callTopRatedMovieApiList(api_key:String,page:Int)
    }
}