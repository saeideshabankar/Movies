package com.example.movies.ui.main.search_frag

import com.example.movies.data.servises.ApiClient
import com.example.movies.ui.base_frag.BasePresenterImpl
import com.example.movies.utils.applyIoScheduler

class SearchPresenterImpl constructor(val view:SearchContract.View):BasePresenterImpl(),SearchContract.Presenter
{

    override fun callSearchApiList(api_key: String, nameOfUserSearch: String) {

        if (view.checkNetWorkConnection()) {

            view.showLoader()
            disposable = ApiClient.getInstance().apiUseCase()
                .getSearchMovieList(api_key,nameOfUserSearch)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoader()

                    //error
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let {

                                    view.loadSearchApiList(it.results)
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