package com.example.movies.data.servises

import com.example.movies.data.models.DetailsListModel
import com.example.movies.data.models.ImagesModel
import com.example.movies.data.models.MoviesListModel
import com.example.movies.data.models.TrailerModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    //get name of movies from  one page
    @GET("movie/popular")
    fun popularMoviesListApi(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Single<Response<MoviesListModel>>

 //get name of movies from without page
    @GET("movie/popular")
    fun popularWithoutPageMoviesListApi(
        @Query("api_key") api_key: String,
    ): Single<Response<MoviesListModel>>

    //get trending movie
    @GET("trending/{media_type}/{time_window}")
    fun trendingMoviesListApi(
        @Path("media_type") moviesKind: String,
        @Path("time_window") moviesWeek: String,
        @Query("api_key") api_key: String
    ): Single<Response<MoviesListModel>>

    //get list of top rated
    @GET("movie/top_rated")
    fun topRatedMoviesListApi(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Single<Response<MoviesListModel>>

 //get list of top rated without page
    @GET("movie/top_rated")
    fun topRatedWithoutPageMoviesListApi(
        @Query("api_key") api_key: String
    ): Single<Response<MoviesListModel>>

    //get list of search
    @GET("search/movie")
    fun searchMoviesListApi(
        @Query("api_key") api_key: String,
        @Query("query") nameOfMovie: String
    ): Single<Response<MoviesListModel>>

    @GET("movie/{movie_id}")
    fun detailsMovieListApi(
        @Path("movie_id") moviesId: Int,
        @Query("api_key") api_key: String
    ): Single<Response<DetailsListModel>>

    @GET("movie/{movie_id}/images")
    fun imagesListApi(
        @Path("movie_id") moviesId: Int,
        @Query("api_key") api_key: String
    ): Single<Response<ImagesModel>>

    @GET("movie/{movie_id}/videos")
    fun trailerMovieListApi(
        @Path("movie_id") moviesId: Int,
        @Query("api_key") api_key: String
    ): Single<Response<TrailerModel>>

}