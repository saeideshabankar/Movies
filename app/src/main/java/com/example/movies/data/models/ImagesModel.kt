package com.example.movies.data.models


import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @SerializedName("backdrops")
    val backdrops: MutableList<Backdrop> = mutableListOf(),
    @SerializedName("id")
    val id: Int = 0, // 550
    @SerializedName("posters")
    val posters: MutableList<Poster> = mutableListOf()
) {
    data class Backdrop(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double = 0.0, // 1.77777777777778
        @SerializedName("file_path")
        val filePath: String = "", // /fCayJrkfRaCRCTh8GqN30f8oyQF.jpg
        @SerializedName("height")
        val height: Int = 0, // 720
        @SerializedName("iso_639_1")
        val iso6391: Any = Any(), // null
        @SerializedName("vote_average")
        val voteAverage: Int = 0, // 0
        @SerializedName("vote_count")
        val voteCount: Int = 0, // 0
        @SerializedName("width")
        val width: Int = 0 // 1280
    )

    data class Poster(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double = 0.0, // 0.666666666666667
        @SerializedName("file_path")
        val filePath: String = "", // /fpemzjF623QVTe98pCVlwwtFC5N.jpg
        @SerializedName("height")
        val height: Int = 0, // 1800
        @SerializedName("iso_639_1")
        val iso6391: String = "", // en
        @SerializedName("vote_average")
        val voteAverage: Int = 0, // 0
        @SerializedName("vote_count")
        val voteCount: Int = 0, // 0
        @SerializedName("width")
        val width: Int = 0 // 1200
    )
}