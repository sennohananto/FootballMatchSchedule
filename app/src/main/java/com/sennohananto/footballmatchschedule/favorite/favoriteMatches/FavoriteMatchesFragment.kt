package com.sennohananto.footballmatchschedule.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.FavoriteAdapter
import com.sennohananto.footballmatchschedule.database.Favorite
import com.sennohananto.footballmatchschedule.detailMatch.DetailMatchActivity
import com.sennohananto.footballmatchschedule.invisible
import com.sennohananto.footballmatchschedule.visible
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.startActivity


class FavoriteFragment : Fragment(), FavoriteView {

    private lateinit var favoritePresenter: FavoritePresenter

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showFavoriteList(data: List<Favorite>?) {
        swipeRefresh.isRefreshing = true
        val adapter = FavoriteAdapter(data!!){
            startActivity<DetailMatchActivity>(
                    "idEvent" to it.eventId
            )
        }
        recvFavorite.adapter = adapter
        swipeRefresh.isRefreshing = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefresh.setOnRefreshListener {
            favoritePresenter.getFavoriteList()
            swipeRefresh.isRefreshing = false
        }
        recvFavorite.layoutManager = LinearLayoutManager(context,LinearLayout.VERTICAL,false)
    }

    override fun onResume() {
        super.onResume()
        favoritePresenter = FavoritePresenter(context!!,this)
        favoritePresenter.getFavoriteList()
    }
}
