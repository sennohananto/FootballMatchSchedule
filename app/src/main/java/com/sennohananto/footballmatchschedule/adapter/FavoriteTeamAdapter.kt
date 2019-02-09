package com.sennohananto.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.database.FavoriteMatch
import com.sennohananto.footballmatchschedule.toDateIndo
import org.jetbrains.anko.find

class FavoriteAdapter (private val favoriteMatches: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit) : RecyclerView.Adapter<ViewHolderFavorite>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavorite {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_row, parent, false)
        return ViewHolderFavorite(view)
    }

    override fun getItemCount(): Int {
        return favoriteMatches.size
    }

    override fun onBindViewHolder(holder: ViewHolderFavorite, position: Int) {
        holder.bindItem(favoriteMatches[position],listener)
    }
}

class ViewHolderFavorite(view: View) : RecyclerView.ViewHolder(view){
    private val tvTanggal: TextView = view.find(R.id.tvDateMatch)
    private val tvTimKiri: TextView = view.find(R.id.tvTeamHome)
    private val tvSkorKiri: TextView = view.find(R.id.tvScoreHome)
    private val tvTimKanan: TextView = view.find(R.id.tvTeamAway)
    private val tvSkorKanan: TextView = view.find(R.id.tvScoreAway)

    fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit){
//        tvTanggal.text = toDateIndo(favoriteMatch.dateMatch!!,"dd/MM/yy")
        tvTanggal.text = toDateIndo(favoriteMatch.dateMatch!!,"yyyy-MM-dd")
        tvTimKiri.text = favoriteMatch.homeName
        tvTimKanan.text = favoriteMatch.awayName
        if(!favoriteMatch.awayScore.equals("null")){
            tvSkorKiri.text = favoriteMatch.homeScore
            tvSkorKanan.text = favoriteMatch.awayScore
        }

        itemView.setOnClickListener {
            listener(favoriteMatch)
        }
    }
}