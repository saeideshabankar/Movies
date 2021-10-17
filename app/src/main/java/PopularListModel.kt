
import com.google.gson.annotations.SerializedName

data class PopularListModel(
    @SerializedName("page")
    val page: Int = 0, // 1
    @SerializedName("results")
    val results: List<Result> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0, // 500
    @SerializedName("total_results")
    val totalResults: Int = 0 // 10000
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean = false, // false
        @SerializedName("backdrop_path")
        val backdropPath: String = "", // /pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg
        @SerializedName("genre_ids")
        val genreIds: List<Int> = listOf(),
        @SerializedName("id")
        val id: Int = 0, // 791373
        @SerializedName("original_language")
        val originalLanguage: String = "", // en
        @SerializedName("original_title")
        val originalTitle: String = "", // Zack Snyder's Justice League
        @SerializedName("overview")
        val overview: String = "", // Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.
        @SerializedName("popularity")
        val popularity: Double = 0.0, // 8416.68
        @SerializedName("poster_path")
        val posterPath: String = "", // /tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg
        @SerializedName("release_date")
        val releaseDate: String = "", // 2021-03-18
        @SerializedName("title")
        val title: String = "", // Zack Snyder's Justice League
        @SerializedName("video")
        val video: Boolean = false, // false
        @SerializedName("vote_average")
        val voteAverage: Double = 0.0, // 8.7
        @SerializedName("vote_count")
        val voteCount: Int = 0 // 3221
    )
}