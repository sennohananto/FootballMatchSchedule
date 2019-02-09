package com.sennohananto.footballmatchschedule.team.teamDetail.players


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.PlayersAdapter
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.showProgressDialog
import com.sennohananto.footballmatchschedule.team.teamDetail.players.playerDetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.support.v4.startActivity

class PlayersFragment : Fragment(), PlayersView {
    internal val presenter:PlayersPresenter = PlayersPresenter(this)

    companion object {
        fun newInstance(idteam: String): PlayersFragment {
            val fragment = PlayersFragment()
            val arguments = Bundle()
            arguments.putString("idteam", idteam)
            fragment.setArguments(arguments)
            return fragment
        }
    }

    override fun showPlayers(players: Players) {
        val adapter = PlayersAdapter(players.player!!){
            startActivity<PlayerDetailActivity>(
                    "player" to it
            )
        }

        recvPlayer.adapter = adapter
    }

    override fun showLoading() {
        showProgressDialog(context!!,"Loading players")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recvPlayer.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        if(arguments!!.containsKey("idteam")){
            presenter.loadPlayers(arguments!!.getString("idteam"))
        }
    }
}
