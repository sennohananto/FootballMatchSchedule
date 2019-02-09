package com.sennohananto.footballmatchschedule.team


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.sennohananto.footballmatchschedule.MainActivity
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.adapter.TeamAdapter
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.matches.League
import com.sennohananto.footballmatchschedule.model.TeamResponse
import com.sennohananto.footballmatchschedule.showProgressDialog
import com.sennohananto.footballmatchschedule.team.searchTeam.SearchTeamActivity
import com.sennohananto.footballmatchschedule.team.teamDetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment : Fragment(), TeamFragmentView {
    override fun showTeamList(teamResponse: TeamResponse) {
        recvTeam.adapter = TeamAdapter(teamResponse.teams!!){
            startActivity<TeamDetailActivity>(
                    "team" to it
            )
        }
    }

    val teamFragmentPresenter = TeamFragmentPresenter(this)

    override fun loadListLeague(listLeague: List<League>) {
        league_spinner.adapter = ArrayAdapter<League>(context, android.R.layout.simple_spinner_dropdown_item, listLeague);
    }

    override fun showLoading() {
        showProgressDialog(context!!,"Please wait")
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolbar)

        setHasOptionsMenu(true)
        league_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                teamFragmentPresenter.getTeamList((league_spinner.selectedItem as League).idLeague!!)
            }
        }

        recvTeam.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        teamFragmentPresenter.getAllLeague()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        val menuInflater = inflater
        menuInflater?.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView

            val searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText
            searchEditText.setTextColor(ContextCompat.getColor(context!!,R.color.white))
            searchEditText.setHintTextColor(ContextCompat.getColor(context!!,R.color.colorAccent))

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    startActivity<SearchTeamActivity>(
                            "query" to searchView.query.toString()
                    )
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.getComponentName()))
        }
        return super.onCreateOptionsMenu(menu,menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_search -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
