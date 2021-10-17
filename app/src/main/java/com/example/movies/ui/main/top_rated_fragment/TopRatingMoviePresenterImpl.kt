package com.example.movies.ui.main.top_rated_fragment

import com.example.movies.data.servises.ApiClient
import com.example.movies.ui.base_frag.BasePresenterImpl
import com.example.movies.utils.applyIoScheduler

class TopRatingMoviePresenterImpl constructor(val view: TopRatedMovieContract.View): BasePresenterImpl(),
    TopRatedMovieContract.Presenter {

    override fun callTopRatedMovieApiList(api_key: String,page:Int) {
        if (view.checkNetWorkConnection()) {

            view.showLoader()
            disposable = ApiClient.getInstance().apiUseCase()
                .getTopRatedMoviesList(api_key,page)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoader()

                    //error
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let {

                                view.loadTopRatedMovieApiList(it.results)
                            }
                        }
                        201 -> {
                            // view.gotoLoginPage()
                        }
                        in 400..499 -> {
                            view.serverError("Error")
                        }
                        422 -> {
                            view.serverError("validation error")
                        }
                        404 -> {
                            view.serverError("Not found")
                        }
                        in 500..599 -> {

                        }
                        503 -> {

                        }
                    }
                }, { error ->
                    view.hideLoader()
                    view.responseError(error)
                })
        } else {
            view.errorNetWorkConnection()
        }
    }

}