package com.sennohananto.footballmatchschedule.matches


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.sennohananto.footballmatchschedule.MainActivity
import com.sennohananto.footballmatchschedule.R
import com.sennohananto.footballmatchschedule.dismissProgressDialog
import com.sennohananto.footballmatchschedule.matches.searchMatch.SearchMatchActivity
import com.sennohananto.footballmatchschedule.showProgressDialog
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.support.v4.startActivity


class MatchesFragment : Fragment(), MatchesFragmentView {


    override fun loadListLeague(listLeague: List<League>) {
        league_spinner.adapter = ArrayAdapter<League>(context, android.R.layout.simple_spinner_dropdown_item, listLeague);
    }

    override fun showLoading() {
        if(isAdded){
            showProgressDialog(context!!,"Fetching league list")
        }
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    private lateinit var matchesPagerAdapter: MatchesPagerAdapter

    private val matchesFragmentPresenter: MatchesFragmentPresenter = MatchesFragmentPresenter(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_matches, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolbar)

        childFragmentManager.let {
            matchesPagerAdapter = MatchesPagerAdapter(it)
        }

        setUpPagerAdapter()
        setHasOptionsMenu(true)

        if(!activity!!.isFinishing)
        matchesFragmentPresenter.getAllLeague()

        league_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (activity as MainActivity).prevMatchCallback.loadPrevMatches((league_spinner.selectedItem as League).idLeague!!)
                (activity as MainActivity).nextMatchCallback.loadNextMatches((league_spinner.selectedItem as League).idLeague!!)
            }
        }
    }

    private fun setUpPagerAdapter() {
        tab_layout.addTab(tab_layout.newTab().setText("Prev Match"))
        tab_layout.addTab(tab_layout.newTab().setText("Next Match"))
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        team_view_pager.adapter = matchesPagerAdapter
        team_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { team_view_pager.currentItem = it.position }
                team_view_pager.currentItem = tab!!.position
            }
        })
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
                    startActivity<SearchMatchActivity>(
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
