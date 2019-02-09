package com.sennohananto.footballmatchschedule

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sennohananto.footballmatchschedule.favorite.FavoriteFragment
import com.sennohananto.footballmatchschedule.matches.MatchesFragment
import com.sennohananto.footballmatchschedule.matches.NextMatchCallback
import com.sennohananto.footballmatchschedule.matches.PrevMatchCallback
import com.sennohananto.footballmatchschedule.team.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private lateinit var matchesFragment: MatchesFragment
    private lateinit var favoriteFragment: Fragment
    private lateinit var teamFragment: TeamFragment
    lateinit var prevMatchCallback: PrevMatchCallback
    lateinit var nextMatchCallback: NextMatchCallback

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                if(!item.isChecked){
//                    supportFragmentManager.beginTransaction().replace(R.id.container,matchesFragment).commit()
                    supportFragmentManager.replaceFragment(R.id.container,MatchesFragment(),"MATCHES")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_team -> {
                if(!item.isChecked){
//                    supportFragmentManager.beginTransaction().replace(R.id.container,teamFragment).commit()
                    supportFragmentManager.replaceFragment(R.id.container,teamFragment,"TEAM")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                if(!item.isChecked){
//                    supportFragmentManager.beginTransaction().replace(R.id.container,favoriteFragment).commit()
                    supportFragmentManager.replaceFragment(R.id.container,FavoriteFragment(),"FAVORITE")
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun setOnPrevMatchRefreshListener(matchCallback: PrevMatchCallback){
        this.prevMatchCallback = matchCallback
    }

    fun setOnNextMatchRefreshListener(matchCallback: NextMatchCallback){
        this.nextMatchCallback = matchCallback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        favoriteFragment = FavoriteFragment()
        teamFragment = TeamFragment()


        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, MatchesFragment(), null)
                    .commit()
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Log.d("IMEI",FootballMatchSchedule.getUniqueIMEIId(this))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)
    }
}
