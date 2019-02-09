package com.sennohananto.footballmatchschedule.matches.prevMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sennohananto.footballmatchschedule.MainActivity
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.MatchAdapter
import com.sennohananto.footballmatchschedule.matches.detailMatch.DetailMatchActivity
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.matches.PrevMatchCallback
import com.sennohananto.footballmatchschedule.model.Event
import com.sennohananto.footballmatchschedule.showProgressDialog
import kotlinx.android.synthetic.main.fragment_prev_match.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class PrevMatchFragment : Fragment(), PrevMatchView, PrevMatchCallback {
    override fun loadPrevMatches(idLeague: String) {
        presenter.getTeamList(idLeague)
    }

    val presenter: PrevMatchPresenter = PrevMatchPresenter(this)
    lateinit var adapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_prev_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recvPrevMatch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        (activity as MainActivity).setOnPrevMatchRefreshListener(this)
    }

    override fun showPrevMatchList(data: List<Event>?) {
        adapter = MatchAdapter(data!!,"prev") {
            startActivity<DetailMatchActivity>(
                    "idEvent" to it.idEvent
            )
        }

        try {
            recvPrevMatch.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun showLoading() {
        showProgressDialog(context!!,"Loading Matches")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }
}
