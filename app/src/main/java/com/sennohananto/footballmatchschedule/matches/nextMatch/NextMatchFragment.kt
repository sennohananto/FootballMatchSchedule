package com.sennohananto.footballmatchschedule.matches.nextMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sennohananto.footballmatchschedule.MainActivity
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.MatchAdapter
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.matches.NextMatchCallback
import com.sennohananto.footballmatchschedule.matches.detailMatch.DetailMatchActivity
import com.sennohananto.footballmatchschedule.model.Event
import com.sennohananto.footballmatchschedule.showProgressDialog
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), NextMatchView, NextMatchCallback {

    override fun loadNextMatches(idLeague: String) {
        presenter.getTeamList(idLeague)
    }

    val presenter: NextMatchPresenter = NextMatchPresenter(this)
    private lateinit var adapter: MatchAdapter

    override fun showLoading() {
        showProgressDialog(context!!,"Loading Matches")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun showNextMatchList(data: List<Event>?) {
        adapter = MatchAdapter(data!!,"next") {
            startActivity<DetailMatchActivity>(
                    "idEvent" to it.idEvent
            )
        }
        recvNextMatch.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmen
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recvNextMatch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        (activity as MainActivity).setOnNextMatchRefreshListener(this)
    }
}
