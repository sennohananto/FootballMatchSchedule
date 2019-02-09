package com.sennohananto.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.team.teamDetail.players.Player
import org.jetbrains.anko.find


class PlayersAdapter (private val players: List<Player>, private val listener: (Player) -> Unit) : RecyclerView.Adapter<ViewHolderPlayer>() {
    override fun onBindViewHolder(holder: ViewHolderPlayer, position: Int) {
        holder.bindItem(holder.itemView.context,players[position],listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPlayer {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_row, parent, false)
        return ViewHolderPlayer(view)
    }

    override fun getItemCount(): Int {
        return players.size
    }
}

class ViewHolderPlayer(view: View) : RecyclerView.ViewHolder(view){
    private val imgTeam: ImageView = view.find(R.id.imgPlayer)
    private val tvPlayerName: TextView = view.find(R.id.tvPlayerName)
    private val tvPlayerPosition: TextView = view.find(R.id.tvPlayerPosition)

    fun bindItem(context: Context, player: Player, listener: (Player) -> Unit){
        tvPlayerName.text = player.strPlayer
        tvPlayerPosition.text = player.strPosition
        Glide.with(context).load(player.strCutout)
                .apply(RequestOptions.errorOf(R.drawable.ic_person_black_24dp))
                .into(imgTeam)
        itemView.setOnClickListener {
            listener(player)
        }
    }

}