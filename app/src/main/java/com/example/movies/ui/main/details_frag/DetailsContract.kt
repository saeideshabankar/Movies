package com.example.movies.ui.main.details_frag

import com.example.movies.data.models.DetailsListModel
import com.example.movies.data.models.TrailerModel
import com.example.movies.ui.base_frag.BasePresenter
import com.example.movies.ui.base_frag.BaseView

interface DetailsContract {
    interface View : BaseView {
        fun loadDetailsApiList(data: DetailsListModel)

        // fun loadImagesApiList(data: MutableList<ImagesModel.Poster>)
        fun loadTrailerApiList(data: MutableList<TrailerModel.Result>)
    }

    interface Presenter : BasePresenter {
        fun callDetailsApiList(page: Int, api_key: String)

        //  fun callImagesListApiList(movieId:Int,api_key:String)
        fun callTrailerListApiList(movieId: Int, api_key: String)
    }
}