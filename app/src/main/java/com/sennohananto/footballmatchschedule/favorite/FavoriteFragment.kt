package com.sennohananto.footballmatchschedule.favorite


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sennohananto.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment(), FavoriteView {

    private val favoritePresenter = FavoritePresenter(this)
    private lateinit var favoritePagerAdapter: FavoritePagerAdapter

    override fun setUpPagerAdapter() {
        tab_layout.addTab(tab_layout.newTab().setText("Matches"))
        tab_layout.addTab(tab_layout.newTab().setText("Teams"))
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        favorite_view_pager.adapter = favoritePagerAdapter
        favorite_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { favorite_view_pager.currentItem = it.position }
                favorite_view_pager.currentItem = tab!!.position
            }
        })
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.let {
            favoritePagerAdapter = FavoritePagerAdapter(it)
        }
        favoritePresenter.setUpPagerAdapter()
    }


}
