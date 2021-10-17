package com.example.movies.data.models


import com.google.gson.annotations.SerializedName

data class TrailerModel(
    @SerializedName("id")
    val id: Int = 0, // 550
    @SerializedName("results")
    val results: MutableList<Result> = mutableListOf()
) {
    data class Result(
        @SerializedName("id")
        val id: String = "", // 533ec654c3a36854480003eb
        @SerializedName("iso_3166_1")
        val iso31661: String = "", // US
        @SerializedName("iso_639_1")
        val iso6391: String = "", // en
        @SerializedName("key")
        val key: String = "", // SUXWAEX2jlg
        @SerializedName("name")
        val name: String = "", // Trailer 1
        @SerializedName("site")
        val site: String = "", // YouTube
        @SerializedName("size")
        val size: Int = 0, // 720
        @SerializedName("type")
        val type: String = "" // Trailer
    )
}