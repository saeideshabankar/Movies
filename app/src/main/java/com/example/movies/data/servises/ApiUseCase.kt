package com.example.movies.data.servises

import com.example.movies.data.models.DetailsListModel
import com.example.movies.data.models.ImagesModel
import com.example.movies.data.models.MoviesListModel
import com.example.movies.data.models.TrailerModel
import io.reactivex.Single
import retrofit2.Response

open class ApiUseCase constructor(private val apiServices: ApiServices) {

    fun getPopularMoviesList(api_key: String, page: Int): Single<Response<MoviesListModel>> {
        return apiServices.popularMoviesListApi(api_key, page)
    }
    fun popularWithoutPageMoviesListApi(api_key: String): Single<Response<MoviesListModel>> {
        return apiServices.popularWithoutPageMoviesListApi(api_key)
    }
    fun getTrendingMoviesListApi(
        movieKind: String,
        movieDaily: String,
        api_key: String
    ): Single<Response<MoviesListModel>> {
        return apiServices.trendingMoviesListApi(movieKind, movieDaily, api_key)
    }
    fun getTopRatedMoviesList(api_key: String, page: Int): Single<Response<MoviesListModel>> {
        return apiServices.topRatedMoviesListApi(api_key, page)
    }
    fun topRatedWithoutPageMoviesListApi(api_key: String): Single<Response<MoviesListModel>> {
        return apiServices.topRatedWithoutPageMoviesListApi(api_key)
    }
    fun getDetailsMovieList(movieId: Int, api_key: String): Single<Response<DetailsListModel>> {
        return apiServices.detailsMovieListApi(movieId, api_key)
    }
    fun getSearchMovieList(
        api_key: String,
        nameOfMovie: String
    ): Single<Response<MoviesListModel>> {
        return apiServices.searchMoviesListApi(api_key, nameOfMovie)
    }
    fun getImagesList(movieId: Int, api_key: String): Single<Response<ImagesModel>> {
        return apiServices.imagesListApi(movieId, api_key)
    }
    fun getTrailer(movieId: Int, api_key: String): Single<Response<TrailerModel>> {
        return apiServices.trailerMovieListApi(movieId, api_key)
    }
}