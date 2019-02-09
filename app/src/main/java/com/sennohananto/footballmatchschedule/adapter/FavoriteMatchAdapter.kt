package com.sennohananto.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.database.FavoriteMatch
import com.sennohananto.footballmatchschedule.toDateIndo
import com.sennohananto.footballmatchschedule.toHourIndo
import org.jetbrains.anko.find

class FavoriteMatchAdapter (private val favoriteMatches: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit) : RecyclerView.Adapter<ViewHolderFavoriteMatch>() {
    override fun onBindViewHolder(holder: ViewHolderFavoriteMatch, position: Int) {
        holder.bindItem(favoriteMatches[position],listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoriteMatch {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_row, parent, false)
        return ViewHolderFavoriteMatch(view)
    }

    override fun getItemCount(): Int {
        return favoriteMatches.size
    }
}

class ViewHolderFavoriteMatch(view: View) : RecyclerView.ViewHolder(view){
    private val tvTanggal: TextView = view.find(R.id.tvDateMatch)
    private val tvTimKiri: TextView = view.find(R.id.tvTeamHome)
    private val tvSkorKiri: TextView = view.find(R.id.tvScoreHome)
    private val tvTimKanan: TextView = view.find(R.id.tvTeamAway)
    private val tvSkorKanan: TextView = view.find(R.id.tvScoreAway)
    private val tvHourMatch: TextView = view.find(R.id.tvHourMatch)

    fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit){
        tvTanggal.text = toDateIndo(favoriteMatch.dateMatch!!,"yyyy-MM-dd")
        tvTimKiri.text = favoriteMatch.homeName
        tvTimKanan.text = favoriteMatch.awayName
        tvHourMatch.text = toHourIndo(favoriteMatch.strTime.toString())
        if(!favoriteMatch.awayScore.equals("null")){
            tvSkorKiri.text = favoriteMatch.homeScore
            tvSkorKanan.text = favoriteMatch.awayScore
        }

        itemView.setOnClickListener {
            listener(favoriteMatch)
        }
    }
}