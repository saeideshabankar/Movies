package com.example.movies.ui.main.popular_frag

import android.util.Log
import com.example.movies.data.servises.ApiClient
import com.example.movies.ui.base_frag.BasePresenterImpl
import com.example.movies.utils.applyIoScheduler

class PopularPresenterImpl constructor(val view: PopularContract.View) : BasePresenterImpl(),
    PopularContract.Presenter {
    override fun callPopularApiList(api_key: String, page: Int) {
        if (view.checkNetWorkConnection()) {

            view.showLoader()
            disposable = ApiClient.getInstance().apiUseCase()
                .getPopularMoviesList(api_key, page)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoader()

                    //error
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let {

                                Log.i("TAG5", "callImagesListApiList: ${it.results}")
                                view.loadPopularApiList(it.results)
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