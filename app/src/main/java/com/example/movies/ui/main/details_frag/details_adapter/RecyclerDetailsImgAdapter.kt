package com.example.movies.ui.main.details_frag.details_adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R

class RecyclerDetailsImgAdapter(
    private val context: Context,
    private val imgMoviesList: MutableList<Int>
) :
    RecyclerView.Adapter<RecyclerDetailsImgAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_detals_frag_img, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(imgMoviesList[position])
    }

    override fun getItemCount(): Int {
        return imgMoviesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImg = itemView.findViewById(R.id.row_details_img1) as ImageView
        fun bindData(data: Int) {

//          var imgPath = "https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.png"
            Glide.with(context)
                .load(data)
                .into(movieImg)

            val dialog = Dialog(context)

            movieImg.setOnClickListener {
                dialog.setContentView(R.layout.dialog_show_img)
                val imgCloseDialogView = dialog.findViewById(R.id.dialog_close_img) as ImageView
                val imgDialogView = dialog.findViewById(R.id.dialog_img) as ImageView
                Glide.with(context)
                    .load(data)
                    .into(imgDialogView)

                imgCloseDialogView.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }
        }
    }
}
