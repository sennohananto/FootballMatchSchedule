package com.sennohananto.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.model.Event
import com.sennohananto.footballmatchschedule.toDateIndo
import org.jetbrains.anko.find

class MatchAdapter (private val match: List<Event>, private val listener: (Event) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(match[position],listener)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val tvTanggal: TextView = view.find(R.id.tvDateMatch)
    private val tvTimKiri: TextView = view.find(R.id.tvTeamHome)
    private val tvSkorKiri: TextView = view.find(R.id.tvScoreHome)
    private val tvTimKanan: TextView = view.find(R.id.tvTeamAway)
    private val tvSkorKanan: TextView = view.find(R.id.tvScoreAway)

    fun bindItem(event: Event, listener: (Event) -> Unit){
        tvTanggal.text = toDateIndo(event.strDate!!,"dd/MM/yy")
        tvTimKiri.text = event.strHomeTeam
        tvSkorKiri.text = event.intHomeScore?.toString()
        tvTimKanan.text = event.strAwayTeam
        tvSkorKanan.text = event.intAwayScore?.toString()
        itemView.setOnClickListener {
            listener(event)
        }
    }
}