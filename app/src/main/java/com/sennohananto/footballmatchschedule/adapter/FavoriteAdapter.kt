package com.sennohananto.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.database.Favorite
import com.sennohananto.footballmatchschedule.toDateIndo
import org.jetbrains.anko.find

class FavoriteAdapter (private val favorites: List<Favorite>, private val listener: (Favorite) -> Unit) : RecyclerView.Adapter<ViewHolderFavorite>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavorite {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_row, parent, false)
        return ViewHolderFavorite(view)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: ViewHolderFavorite, position: Int) {
        holder.bindItem(favorites[position],listener)
    }
}

class ViewHolderFavorite(view: View) : RecyclerView.ViewHolder(view){
    private val tvTanggal: TextView = view.find(R.id.tvDateMatch)
    private val tvTimKiri: TextView = view.find(R.id.tvTeamHome)
    private val tvSkorKiri: TextView = view.find(R.id.tvScoreHome)
    private val tvTimKanan: TextView = view.find(R.id.tvTeamAway)
    private val tvSkorKanan: TextView = view.find(R.id.tvScoreAway)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit){
        tvTanggal.text = toDateIndo(favorite.dateMatch!!,"dd/MM/yy")
        tvTimKiri.text = favorite.homeName
        tvTimKanan.text = favorite.awayName
        if(!favorite.awayScore.equals("null")){
            tvSkorKiri.text = favorite.homeScore
            tvSkorKanan.text = favorite.awayScore
        }

        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}