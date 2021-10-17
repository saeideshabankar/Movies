package com.example.movies.data.models


import com.google.gson.annotations.SerializedName

data class DetailsListModel(
    @SerializedName("adult")
    val adult: Boolean = false, // false
    @SerializedName("backdrop_path")
    val backdropPath: String = "", // /fCayJrkfRaCRCTh8GqN30f8oyQF.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any = Any(), // null
    @SerializedName("budget")
    val budget: Int = 0, // 63000000
    @SerializedName("genres")
    val genres: MutableList<Genre> = mutableListOf(),
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Int = 0, // 550
    @SerializedName("imdb_id")
    val imdbId: String = "", // tt0137523
    @SerializedName("original_language")
    val originalLanguage: String = "", // en
    @SerializedName("original_title")
    val originalTitle: String = "", // Fight Club
    @SerializedName("overview")
    val overview: String = "", // A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground "fight clubs" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.
    @SerializedName("popularity")
    val popularity: Double = 0.0, // 0.5
    @SerializedName("poster_path")
    val posterPath: Any = Any(), // null
    @SerializedName("production_companies")
    val productionCompanies: MutableList<ProductionCompany> = mutableListOf(),
    @SerializedName("production_countries")
    val productionCountries: MutableList<ProductionCountry> = mutableListOf(),
    @SerializedName("release_date")
    val releaseDate: String = "", // 1999-10-12
    @SerializedName("revenue")
    val revenue: Int = 0, // 100853753
    @SerializedName("runtime")
    val runtime: Int = 0, // 139
    @SerializedName("spoken_languages")
    val spokenLanguages: MutableList<SpokenLanguage> = mutableListOf(),
    @SerializedName("status")
    val status: String = "", // Released
    @SerializedName("tagline")
    val tagline: String = "", // How much can you know about yourself if you've never been in a fight?
    @SerializedName("title")
    val title: String = "", // Fight Club
    @SerializedName("video")
    val video: Boolean = false, // false
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0, // 7.8
    @SerializedName("vote_count")
    val voteCount: Int = 0 // 3439
) {
    data class Genre(
        @SerializedName("id")
        val id: Int = 0, // 18
        @SerializedName("name")
        val name: String = "" // Drama
    )

    data class ProductionCompany(
        @SerializedName("id")
        val id: Int = 0, // 508
        @SerializedName("logo_path")
        val logoPath: String = "", // /7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png
        @SerializedName("name")
        val name: String = "", // Regency Enterprises
        @SerializedName("origin_country")
        val originCountry: String = "" // US
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        val iso31661: String = "", // US
        @SerializedName("name")
        val name: String = "" // United States of America
    )

    data class SpokenLanguage(
        @SerializedName("iso_639_1")
        val iso6391: String = "", // en
        @SerializedName("name")
        val name: String = "" // English
    )
}