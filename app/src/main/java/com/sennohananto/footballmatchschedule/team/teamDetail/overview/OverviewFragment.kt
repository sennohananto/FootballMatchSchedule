package com.sennohananto.footballmatchschedule.team.teamDetail.overview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sennohananto.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_overview.*


class OverviewFragment : Fragment(), OverviewView{
    private val presenter:OverviewPresenter = OverviewPresenter(this)

    companion object {
        fun newInstance(overview: String): OverviewFragment {
            val fragment = OverviewFragment()
            val arguments = Bundle()
            arguments.putString("overview", overview)
            fragment.setArguments(arguments)
            return fragment
        }
    }

    override fun loadOverview(overview: String) {
        webView.loadData(overview,"text/html", "UTF-8")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        if(arguments!!.containsKey("overview")){
            presenter.loadOverview(arguments!!.getString("overview"))
        }
    }

}
