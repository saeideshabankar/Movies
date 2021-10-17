package com.example.movies.ui.main.details_frag.details_adapter

import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.data.models.TrailerModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class RecyclerTrailerTextAdapter(
    private val context: Context,
    var moviesDetailsTrailerListModel: MutableList<TrailerModel.Result>
) :
    RecyclerView.Adapter<RecyclerTrailerTextAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_details_frag_trailer_text, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(moviesDetailsTrailerListModel[position])
    }

    override fun getItemCount(): Int {
        return moviesDetailsTrailerListModel.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTrailer = itemView.findViewById(R.id.tvTrailer) as TextView

        fun bindData(trailer: TrailerModel.Result) {
            tvTrailer.text = trailer.name
            tvTrailer.setOnClickListener {
                val dialog = Dialog(context)
                dialog.setContentView(R.layout.dialog_show_trailer)
                val youTubePlayerView: YouTubePlayerView =
                    dialog.findViewById(R.id.youtube_player_view)

                youTubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                        val videoId = trailer.key
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })

                dialog.show()
                dialog.setOnDismissListener {
                    youTubePlayerView.release()
                }
            }
        }
    }

}