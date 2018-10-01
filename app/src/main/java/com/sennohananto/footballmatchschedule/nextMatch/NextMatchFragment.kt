package com.sennohananto.footballmatchschedule.nextMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.MatchAdapter
import com.sennohananto.footballmatchschedule.detailMatch.DetailMatchActivity
import com.sennohananto.footballmatchschedule.invisible
import com.sennohananto.footballmatchschedule.model.Event
import com.sennohananto.footballmatchschedule.visible
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(),NextMatchView {
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: MatchAdapter

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Event>?) {
        adapter = MatchAdapter(data!!) {
            startActivity<DetailMatchActivity>(
                    "idEvent" to it.idEvent
            )
        }
        recvNextMatch.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_next_match, container, false)
        presenter = NextMatchPresenter(this)
        return v
    }

    override fun onResume() {
        super.onResume()
        super.onResume()
        recvNextMatch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        presenter.getTeamList("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
    }


}
