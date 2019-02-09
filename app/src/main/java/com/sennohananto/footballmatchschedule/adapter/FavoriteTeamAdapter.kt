package com.sennohananto.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.database.FavoriteTeam
import org.jetbrains.anko.find

class FavoriteTeamAdapter (private val favoriteTeams: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit) : RecyclerView.Adapter<ViewHolderFavoriteTeam>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoriteTeam {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_row, parent, false)
        return ViewHolderFavoriteTeam(view)
    }

    override fun getItemCount(): Int {
        return favoriteTeams.size
    }

    override fun onBindViewHolder(holderTeam: ViewHolderFavoriteTeam, position: Int) {
        holderTeam.bindItem(holderTeam.itemView.context,favoriteTeams[position],listener)
    }
}

class ViewHolderFavoriteTeam(view: View) : RecyclerView.ViewHolder(view){
    private val imgTeam: ImageView = view.find(R.id.imgTeam)
    private val tvTeamName: TextView = view.find(R.id.tvTeamName)

    fun bindItem(context: Context, favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit){
        Glide.with(context).load(favoriteTeam.strTeamBadge).into(imgTeam)
        tvTeamName.text = favoriteTeam.strTeam
        itemView.setOnClickListener {
            listener(favoriteTeam)
        }
    }
}