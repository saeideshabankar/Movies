package com.example.movies.ui.main.search_frag

import com.example.movies.data.models.MoviesListModel
import com.example.movies.ui.base_frag.BasePresenter
import com.example.movies.ui.base_frag.BaseView

interface SearchContract {
    interface View : BaseView {
        fun loadSearchApiList(data: MutableList<MoviesListModel.Result>)
    }

    interface Presenter : BasePresenter {
        fun callSearchApiList(api_key: String, nameOfUserSearch: String)
    }
}