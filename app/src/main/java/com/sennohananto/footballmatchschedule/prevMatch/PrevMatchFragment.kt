package com.sennohananto.footballmatchschedule.prevMatch


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
import kotlinx.android.synthetic.main.fragment_prev_match.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class PrevMatchFragment : Fragment(), PrevMatchView {

    private lateinit var presenter: PrevMatchPresenter
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_prev_match, container, false)
        presenter = PrevMatchPresenter(this)
        return v
    }

    override fun onResume() {
        super.onResume()
//        Log.d("UUID","UUID Sekarang : "+ UUID.randomUUID().toString())



        recvPrevMatch.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        presenter.getTeamList("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328")

    }

    override fun showPrevMatchList(data: List<Event>?) {
        adapter = MatchAdapter(data!!) {
            startActivity<DetailMatchActivity>(
                    "idEvent" to it.idEvent
            )
        }
        recvPrevMatch.adapter = adapter
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }


}
