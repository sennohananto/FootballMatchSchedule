package com.sennohananto.footballmatchschedule.team.searchTeam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.TeamAdapter
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.model.TeamResponse
import com.sennohananto.footballmatchschedule.showProgressDialog
import com.sennohananto.footballmatchschedule.team.teamDetail.TeamDetailActivity
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchTeamActivity : AppCompatActivity(),SearchTeamView {

    override fun loadSearchResult(result: TeamResponse) {
        if(result.teams!=null){
            val adapter = TeamAdapter(result.teams!!){
                startActivity<TeamDetailActivity>(
                        "team" to it
                )
            }
            recvSearchResult.adapter = adapter
        }else{
            toast("No Result")
        }

    }

    val presenter:SearchTeamPresenter = SearchTeamPresenter(this)

    override fun querySearch(keyword: String) {
        presenter.querySearch(keyword)
    }


    override fun showLoading() {
        showProgressDialog(this,"Searching")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
        recvSearchResult.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        querySearch(intent.getStringExtra("query"))
    }
}
