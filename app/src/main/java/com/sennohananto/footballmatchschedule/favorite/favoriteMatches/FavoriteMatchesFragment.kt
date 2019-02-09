package com.sennohananto.footballmatchschedule.favorite.favoriteMatches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.FavoriteMatchAdapter
import com.sennohananto.footballmatchschedule.database.FavoriteMatch
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.matches.detailMatch.DetailMatchActivity
import com.sennohananto.footballmatchschedule.showProgressDialog
import kotlinx.android.synthetic.main.fragment_favorite_matches.*
import org.jetbrains.anko.support.v4.startActivity


class FavoriteMatchesFragment : Fragment(), FavoriteMatchesView {

    private lateinit var favoriteMatchesPresenter: FavoriteMatchesPresenter

    override fun showLoading() {
        showProgressDialog(context!!,"Loading")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun showFavoriteMatchesList(data: List<FavoriteMatch>?) {
        swipeRefreshMatch.isRefreshing = true
        val adapter = FavoriteMatchAdapter(data!!){
            startActivity<DetailMatchActivity>(
                    "idEvent" to it.eventId
            )
        }
        recvFavoriteMatches.adapter = adapter
        swipeRefreshMatch.isRefreshing = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshMatch.setOnRefreshListener {
            favoriteMatchesPresenter.getFavoriteList()
            swipeRefreshMatch.isRefreshing = false
        }
        recvFavoriteMatches.layoutManager = LinearLayoutManager(context,LinearLayout.VERTICAL,false)

        favoriteMatchesPresenter = FavoriteMatchesPresenter(context!!, this)
        favoriteMatchesPresenter.getFavoriteList()
    }

    override fun onResume() {
        super.onResume()
        favoriteMatchesPresenter.getFavoriteList()
    }
}
