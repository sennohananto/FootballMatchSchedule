package com.sennohananto.footballmatchschedule.team.teamDetail.players.playerDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.team.teamDetail.players.Player
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {
    val presenter: PlayerDetailPresenter = PlayerDetailPresenter(this)

    override fun showPlayers(player: Player) {
        Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions.errorOf(R.drawable.ic_person_black_24dp))
                .load(player.strFanart1).into(imgPlayer)
        tvPlayerHeight.text = player.strHeight
        tvPlayerWeight.text = player.strWeight
        tvPlayerDesc.text = player.strDescriptionEN
        tvPlayerPosition.text = player.strPosition
        supportActionBar?.title = player.strPlayer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
        val player = intent.getSerializableExtra("player") as Player
        presenter.showPlayerInfo(player)
    }
}