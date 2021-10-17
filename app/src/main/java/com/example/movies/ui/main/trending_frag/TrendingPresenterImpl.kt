package com.example.movies.ui.main.trending_frag

import com.example.movies.data.servises.ApiClient
import com.example.movies.ui.base_frag.BasePresenterImpl
import com.example.movies.utils.applyIoScheduler

class TrendingPresenterImpl constructor(val view: TrendingContract.View) : BasePresenterImpl(),
    TrendingContract.Presenter {

    override fun callTrendingApiList(movieKind: String, movieDaily: String, api_key: String) {
        if (view.checkNetWorkConnection()) {

            view.showLoader()
            disposable = ApiClient.getInstance().apiUseCase()
                .getTrendingMoviesListApi(movieKind, movieDaily, api_key)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoader()

                    //error
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let {

                                view.loadTrendingApiList(it.results)
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