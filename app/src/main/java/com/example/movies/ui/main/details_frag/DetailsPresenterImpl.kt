package com.example.movies.ui.main.details_frag

import android.util.Log
import com.example.movies.data.servises.ApiClient
import com.example.movies.ui.base_frag.BasePresenterImpl
import com.example.movies.utils.applyIoScheduler

class DetailsPresenterImpl constructor(val view: DetailsContract.View) : BasePresenterImpl(),
    DetailsContract.Presenter {
    override fun callDetailsApiList(movieId: Int, api_key: String) {
        if (view.checkNetWorkConnection()) {
            view.showLoader()
            disposable = ApiClient.getInstance().apiUseCase()
                .getDetailsMovieList(movieId, api_key)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoader()
                    //error
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let {
                                view.loadDetailsApiList(it)
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

    /*override fun callImagesListApiList(movieId: Int, api_key: String) {
        if (view.checkNetWorkConnection()) {

            view.showLoader()
            disposable = ApiClient.getInstance().apiUseCase()
                .getImagesList(movieId,api_key)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoader()

                    //error
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let {

                                Log.i("salam", "callImagesListApiList: ${it.id}")
                                view.loadImagesApiList(it.posters)
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
    }*/
    override fun callTrailerListApiList(movieId: Int, api_key: String) {
        if (view.checkNetWorkConnection()) {

            view.showLoader()
            disposable = ApiClient.getInstance().apiUseCase()
                .getTrailer(movieId, api_key)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoader()

                    //error
                    when (response.code()) {
                        in 200..299 -> {
                            response.body()?.let {

                                Log.i("salam", "callImagesListApiList: ${it.id}")
                                view.loadTrailerApiList(it.results)
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
