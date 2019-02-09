package com.sennohananto.footballmatchschedule


import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewInteraction
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.v7.widget.RecyclerView.ViewHolder
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers.*

import com.sennohananto.footballmatchschedule.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class AllTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun allTest() {
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val viewPager = onView(
allOf(withId(R.id.team_view_pager),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
2),
isDisplayed()))
        viewPager.perform(swipeLeft())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatSpinner = onView(
allOf(withId(R.id.league_spinner),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.LinearLayout")),
1),
0),
isDisplayed()))
        appCompatSpinner.perform(click())
        
        val appCompatCheckedTextView = onData(anything())
.inAdapterView(childAtPosition(
withClassName(`is`("android.widget.PopupWindow$PopupBackgroundView")),
0))
.atPosition(1)
        appCompatCheckedTextView.perform(click())
        
        val viewPager2 = onView(
allOf(withId(R.id.team_view_pager),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
2),
isDisplayed()))
        viewPager2.perform(swipeRight())
        
        val recyclerView = onView(
allOf(withId(R.id.recvPrevMatch),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(6, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView = onView(
allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
2),
0),
isDisplayed()))
        actionMenuItemView.perform(click())
        
        pressBack()
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val viewPager3 = onView(
allOf(withId(R.id.team_view_pager),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
2),
isDisplayed()))
        viewPager3.perform(swipeLeft())
        
        val recyclerView2 = onView(
allOf(withId(R.id.recvNextMatch),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(4, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView2 = onView(
allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
2),
0),
isDisplayed()))
        actionMenuItemView2.perform(click())
        
        pressBack()
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView3 = onView(
allOf(withId(R.id.action_search), withContentDescription("Search"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
1),
0),
isDisplayed()))
        actionMenuItemView3.perform(click())
        
        val searchAutoComplete = onView(
allOf(withId(R.id.search_src_text),
childAtPosition(
allOf(withId(R.id.search_plate),
childAtPosition(
withId(R.id.search_edit_frame),
1)),
0),
isDisplayed()))
        searchAutoComplete.perform(replaceText("United"), closeSoftKeyboard())
        
        val searchAutoComplete2 = onView(
allOf(withId(R.id.search_src_text), withText("United"),
childAtPosition(
allOf(withId(R.id.search_plate),
childAtPosition(
withId(R.id.search_edit_frame),
1)),
0),
isDisplayed()))
        searchAutoComplete2.perform(pressImeActionButton())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val recyclerView3 = onView(
allOf(withId(R.id.recvSearchResult),
childAtPosition(
withClassName(`is`("android.widget.LinearLayout")),
1)))
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView4 = onView(
allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
2),
0),
isDisplayed()))
        actionMenuItemView4.perform(click())
        
        val appCompatImageButton = onView(
allOf(withContentDescription("Navigasi naik"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withId(R.id.appbar),
0)),
1),
isDisplayed()))
        appCompatImageButton.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        pressBack()
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton2 = onView(
allOf(withContentDescription("Ciutkan"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withClassName(`is`("android.support.design.widget.AppBarLayout")),
0)),
1),
isDisplayed()))
        appCompatImageButton2.perform(click())
        
        val bottomNavigationItemView = onView(
allOf(withId(R.id.navigation_team),
childAtPosition(
childAtPosition(
withId(R.id.navigation),
0),
1),
isDisplayed()))
        bottomNavigationItemView.perform(click())
        
        val appCompatSpinner2 = onView(
allOf(withId(R.id.league_spinner),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.LinearLayout")),
1),
0),
isDisplayed()))
        appCompatSpinner2.perform(click())
        
        val appCompatCheckedTextView2 = onData(anything())
.inAdapterView(childAtPosition(
withClassName(`is`("android.widget.PopupWindow$PopupBackgroundView")),
0))
.atPosition(1)
        appCompatCheckedTextView2.perform(click())
        
        val recyclerView4 = onView(
allOf(withId(R.id.recvTeam),
childAtPosition(
withClassName(`is`("android.widget.LinearLayout")),
2)))
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(2, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val viewPager4 = onView(
allOf(withId(R.id.team_view_pager),
childAtPosition(
allOf(withId(R.id.rootViewTeamDetail),
childAtPosition(
withId(android.R.id.content),
0)),
1),
isDisplayed()))
        viewPager4.perform(swipeLeft())
        
        val actionMenuItemView5 = onView(
allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
2),
0),
isDisplayed()))
        actionMenuItemView5.perform(click())
        
        val recyclerView5 = onView(
allOf(withId(R.id.recvPlayer),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0)))
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(5, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton3 = onView(
allOf(withContentDescription("Navigasi naik"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withId(R.id.appbar),
0)),
1),
isDisplayed()))
        appCompatImageButton3.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton4 = onView(
allOf(withContentDescription("Navigasi naik"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withId(R.id.appbar),
0)),
1),
isDisplayed()))
        appCompatImageButton4.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView6 = onView(
allOf(withId(R.id.action_search), withContentDescription("Search"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
1),
0),
isDisplayed()))
        actionMenuItemView6.perform(click())
        
        val searchAutoComplete3 = onView(
allOf(withId(R.id.search_src_text),
childAtPosition(
allOf(withId(R.id.search_plate),
childAtPosition(
withId(R.id.search_edit_frame),
1)),
0),
isDisplayed()))
        searchAutoComplete3.perform(replaceText("Real"), closeSoftKeyboard())
        
        val searchAutoComplete4 = onView(
allOf(withId(R.id.search_src_text), withText("Real"),
childAtPosition(
allOf(withId(R.id.search_plate),
childAtPosition(
withId(R.id.search_edit_frame),
1)),
0),
isDisplayed()))
        searchAutoComplete4.perform(pressImeActionButton())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val recyclerView6 = onView(
allOf(withId(R.id.recvSearchResult),
childAtPosition(
withClassName(`is`("android.widget.LinearLayout")),
1)))
        recyclerView6.perform(actionOnItemAtPosition<ViewHolder>(4, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView7 = onView(
allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
2),
0),
isDisplayed()))
        actionMenuItemView7.perform(click())
        
        val tabView = onView(
allOf(childAtPosition(
childAtPosition(
withId(R.id.tab_layout),
0),
1),
isDisplayed()))
        tabView.perform(click())
        
        val viewPager5 = onView(
allOf(withId(R.id.team_view_pager),
childAtPosition(
allOf(withId(R.id.rootViewTeamDetail),
childAtPosition(
withId(android.R.id.content),
0)),
1),
isDisplayed()))
        viewPager5.perform(swipeLeft())
        
        val recyclerView7 = onView(
allOf(withId(R.id.recvPlayer),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0)))
        recyclerView7.perform(actionOnItemAtPosition<ViewHolder>(4, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton5 = onView(
allOf(withContentDescription("Navigasi naik"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withId(R.id.appbar),
0)),
1),
isDisplayed()))
        appCompatImageButton5.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val recyclerView8 = onView(
allOf(withId(R.id.recvPlayer),
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0)))
        recyclerView8.perform(actionOnItemAtPosition<ViewHolder>(2, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton6 = onView(
allOf(withContentDescription("Navigasi naik"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withId(R.id.appbar),
0)),
1),
isDisplayed()))
        appCompatImageButton6.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton7 = onView(
allOf(withContentDescription("Navigasi naik"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withId(R.id.appbar),
0)),
1),
isDisplayed()))
        appCompatImageButton7.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton8 = onView(
allOf(withContentDescription("Navigasi naik"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withClassName(`is`("android.support.design.widget.AppBarLayout")),
0)),
1),
isDisplayed()))
        appCompatImageButton8.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val appCompatImageButton9 = onView(
allOf(withContentDescription("Ciutkan"),
childAtPosition(
allOf(withId(R.id.toolbar),
childAtPosition(
withClassName(`is`("android.support.design.widget.AppBarLayout")),
0)),
1),
isDisplayed()))
        appCompatImageButton9.perform(click())
        
        val bottomNavigationItemView2 = onView(
allOf(withId(R.id.navigation_favorites),
childAtPosition(
childAtPosition(
withId(R.id.navigation),
0),
2),
isDisplayed()))
        bottomNavigationItemView2.perform(click())
        
        val recyclerView9 = onView(
allOf(withId(R.id.recvFavorite),
childAtPosition(
withId(R.id.swipeRefresh),
0)))
        recyclerView9.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView8 = onView(
allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
2),
0),
isDisplayed()))
        actionMenuItemView8.perform(click())
        
        pressBack()
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val tabView2 = onView(
allOf(childAtPosition(
childAtPosition(
withId(R.id.tab_layout),
0),
1),
isDisplayed()))
        tabView2.perform(click())
        
        val viewPager6 = onView(
allOf(withId(R.id.favorite_view_pager),
childAtPosition(
childAtPosition(
withId(R.id.container),
0),
1),
isDisplayed()))
        viewPager6.perform(swipeLeft())
        
        val recyclerView10 = onView(
allOf(withId(R.id.recvFavorite),
childAtPosition(
withId(R.id.swipeRefresh),
0)))
        recyclerView10.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(7000)
        
        val actionMenuItemView9 = onView(
allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
childAtPosition(
childAtPosition(
withId(R.id.toolbar),
2),
0),
isDisplayed()))
        actionMenuItemView9.perform(click())
        
        pressBack()
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
