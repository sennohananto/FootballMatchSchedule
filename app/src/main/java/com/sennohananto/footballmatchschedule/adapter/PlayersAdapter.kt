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
import com.sennohananto.footballmatchschedule.model.Team
import org.jetbrains.anko.find


class TeamAdapter (private val teams: List<Team>, private val listener: (Team) -> Unit) : RecyclerView.Adapter<ViewHolderTeam>() {
    override fun onBindViewHolder(holder: ViewHolderTeam, position: Int) {
        holder.bindItem(holder.itemView.context,teams[position],listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTeam {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_row, parent, false)
        return ViewHolderTeam(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }


}

class ViewHolderTeam(view: View) : RecyclerView.ViewHolder(view){
    private val imgTeam: ImageView = view.find(R.id.imgTeam)
    private val tvTeamName: TextView = view.find(R.id.tvTeamName)

    fun bindItem(context: Context, team: Team, listener: (Team) -> Unit){
        Glide.with(context).load(team.strTeamBadge).into(imgTeam)
        tvTeamName.text = team.strTeam
        itemView.setOnClickListener {
            listener(team)
        }
    }

}