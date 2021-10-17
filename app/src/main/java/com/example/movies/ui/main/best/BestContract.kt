package com.example.movies.ui.main.best

import com.example.movies.data.models.DetailsListModel
import com.example.movies.data.models.MoviesListModel
import com.example.movies.data.models.TrailerModel
import com.example.movies.ui.base_frag.BasePresenter
import com.example.movies.ui.base_frag.BaseView

interface BestContract {
    interface View : BaseView {

        fun loadPopularApiList(data: MutableList<MoviesListModel.Result>)
        fun loadTopRatedMovieApiList(data: MutableList<MoviesListModel.Result>)
        fun loadTrendingApiList(data: MutableList<MoviesListModel.Result>)
    }

    interface Presenter : BasePresenter {
        fun popularWithoutPageMoviesListApi(api_key: String)
        fun topRatedWithoutPageMoviesListApi(api_key:String)
        fun callTrendingApiList(movieKind:String,movieDaily:String,api_key:String)
    }
}