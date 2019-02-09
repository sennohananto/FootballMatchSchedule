package com.sennohananto.footballmatchschedule.adapter

import android.content.Intent
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.getLocaleCalendar
import com.sennohananto.footballmatchschedule.model.Event
import com.sennohananto.footballmatchschedule.toDateIndo
import com.sennohananto.footballmatchschedule.toHourIndo
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*


class MatchAdapter (private val match: List<Event>, private val timeMatch:String, private val listener: (Event) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(match[position],listener)
        if(timeMatch.equals("next")){
            holder.showAddToCalendar()
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val tvTanggal: TextView = view.find(R.id.tvDateMatch)
    private val tvTimKiri: TextView = view.find(R.id.tvTeamHome)
    private val tvSkorKiri: TextView = view.find(R.id.tvScoreHome)
    private val tvTimKanan: TextView = view.find(R.id.tvTeamAway)
    private val tvSkorKanan: TextView = view.find(R.id.tvScoreAway)
    private val tvHourMatch: TextView = view.find(R.id.tvHourMatch)
    private val imgAddToCalendar: ImageView = view.find(R.id.imgAddToCalendar)

    fun bindItem(event: Event, listener: (Event) -> Unit){
        tvTanggal.text = toDateIndo(event.dateEvent!!,"yyyy-MM-dd")
        tvTimKiri.text = event.strHomeTeam
        tvSkorKiri.text = event.intHomeScore?.toString()
        tvTimKanan.text = event.strAwayTeam
        tvSkorKanan.text = event.intAwayScore?.toString()
        tvHourMatch.text = toHourIndo(event.strTime!!)

        imgAddToCalendar.setOnClickListener {

            val beginTime = getLocaleCalendar(event.strDate!!,"dd/MM/yy")

            val sourceFormat = SimpleDateFormat("HH:mm")
            sourceFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            val parsed = sourceFormat.parse(toHourIndo(event.strTime!!)) // => Date is in UTC now


            beginTime?.set(Calendar.HOUR,parsed.hours)
            beginTime?.set(Calendar.MINUTE,parsed.minutes)

            val intent = Intent(Intent.ACTION_INSERT)
                    .setData(Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                            beginTime?.timeInMillis)
                    .putExtra(Events.TITLE, "${event.strHomeTeam} VS ${event.strAwayTeam}")
                    .putExtra(Events.DESCRIPTION, "Dont Missed this Match!")
                    .putExtra(Events.EVENT_LOCATION, "Soccer Field")
                    .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)

            it.context.startActivity(intent)
        }

        itemView.setOnClickListener {
            listener(event)
        }
    }

    fun showAddToCalendar(){
        imgAddToCalendar.visibility = View.VISIBLE
    }
}