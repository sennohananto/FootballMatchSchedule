package com.sennohananto.footballmatchschedule

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.sennohananto.footballmatchschedule.favorite.FavoriteFragment
import com.sennohananto.footballmatchschedule.nextMatch.NextMatchFragment
import com.sennohananto.footballmatchschedule.prevMatch.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var nextMatchFragment: Fragment
    private lateinit var prevMatchFragment: Fragment
    private lateinit var favoriteFragment: Fragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                if(!item.isChecked){
                    supportFragmentManager.beginTransaction().replace(R.id.container,prevMatchFragment).commit()
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                if(!item.isChecked){
                    supportFragmentManager.beginTransaction().replace(R.id.container,nextMatchFragment).commit()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                if(!item.isChecked){
                    supportFragmentManager.beginTransaction().replace(R.id.container,favoriteFragment).commit()
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextMatchFragment = NextMatchFragment()
        prevMatchFragment = PrevMatchFragment()
        favoriteFragment = FavoriteFragment()

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, prevMatchFragment, null)
                    .commit()
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
