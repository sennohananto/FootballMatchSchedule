package com.sennohananto.footballmatchschedule.matches.searchMatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.MatchAdapter
import com.sennohananto.footballmatchschedule.matches.detailMatch.DetailMatchActivity
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.model.SearchResultMatch
import com.sennohananto.footballmatchschedule.showProgressDialog
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class SearchMatchActivity : AppCompatActivity(), SearchMatchView {
    val presenter:SearchMatchPresenter = SearchMatchPresenter(this)

    override fun loadSearchResult(result: SearchResultMatch) {
        if(result.event!=null){

            val adapter = MatchAdapter(result.event!!,"search"){
                startActivity<DetailMatchActivity>(
                        "idEvent" to it.idEvent
                )
            }
            recvSearchResult.adapter = adapter
        }else{
            toast("No result")
        }
    }

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
        setContentView(R.layout.activity_search_match)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        recvSearchResult.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        presenter.querySearch(intent.getStringExtra("query"))
    }
}
