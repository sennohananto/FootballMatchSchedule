package com.sennohananto.footballmatchschedule.favorite.favoriteTeam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.FavoriteTeamAdapter
import com.sennohananto.footballmatchschedule.database.FavoriteTeam
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.showProgressDialog
import com.sennohananto.footballmatchschedule.team.teamDetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.support.v4.startActivity


class FavoriteTeamFragment : Fragment(), FavoriteTeamView {
    lateinit var favoriteTeamPresenter : FavoriteTeamPresenter

    override fun showFavoriteTeamList(data: List<FavoriteTeam>?) {
        swipeRefreshTeam.isRefreshing = true
        val adapter = FavoriteTeamAdapter(data!!){
            startActivity<TeamDetailActivity>(
                    "fromFavorite" to true,
                    "idLeague" to it.idLeague,
                    "idTeam" to it.idTeam,
                    "intFormedYear" to it.intFormedYear,
                    "strDescriptionEN" to it.strDescriptionEN,
                    "strStadium" to it.strStadium,
                    "strTeam" to it.strTeam,
                    "strTeamBadge" to it.strTeamBadge
            )
        }
        recvFavoriteTeam.adapter = adapter
        swipeRefreshTeam.isRefreshing = false
    }

    override fun showLoading() {
        showProgressDialog(context!!,"Loading")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshTeam.setOnRefreshListener {
            favoriteTeamPresenter.getFavoriteTeamList()
            swipeRefreshTeam.isRefreshing = false
        }
        recvFavoriteTeam.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)

        favoriteTeamPresenter = FavoriteTeamPresenter(context!!, this)
        favoriteTeamPresenter.getFavoriteTeamList()
    }

    override fun onResume() {
        super.onResume()
        favoriteTeamPresenter.getFavoriteTeamList()
    }


}
