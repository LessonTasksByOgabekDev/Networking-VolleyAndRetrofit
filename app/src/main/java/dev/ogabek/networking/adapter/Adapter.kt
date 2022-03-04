package dev.ogabek.networking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.ogabek.networking.R
import dev.ogabek.networking.activity.MainActivity
import dev.ogabek.networking.model.Poster

class Adapter(val context: MainActivity, val posts: ArrayList<Poster>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        return PosterViewHolder(view)
    }

    class PosterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ll_poster: LinearLayout = view.findViewById(R.id.ll_poster)
        val tv_title: TextView = view.findViewById(R.id.tv_title)
        val tv_body: TextView = view.findViewById(R.id.tv_body)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val poster = posts[position]

        if (holder is PosterViewHolder) {
            holder.apply {
                tv_title.text = poster.title
                tv_body.text = poster.body
                ll_poster.setOnLongClickListener {
                    context.dialogPoster(poster)
                    false
                }
            }
        }
    }

    override fun getItemCount() = posts.size


}