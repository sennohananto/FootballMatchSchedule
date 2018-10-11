package com.sennohananto.footballmatchschedule


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        onView(isRoot()).perform(waitFor(5000))

        val bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_prev_match),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        onView(isRoot()).perform(waitFor(3000))

        val recyclerView = onView(
                allOf(withId(R.id.recvPrevMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(3000))

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

        onView(isRoot()).perform(waitFor(3000))

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_next_match),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        onView(isRoot()).perform(waitFor(3000))

        val recyclerView2 = onView(
                allOf(withId(R.id.recvNextMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                0)))
        recyclerView2.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(3000))

        val actionMenuItemView = onView(
                allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        onView(isRoot()).perform(waitFor(3000))

        val appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.appbar),
                                                0)),
                                1),
                        isDisplayed()))

        onView(isRoot()).perform(waitFor(3000))
        appCompatImageButton2.perform(click())

        onView(isRoot()).perform(waitFor(3000))

        val bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_favorites),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())

        onView(isRoot()).perform(waitFor(3000))

        val recyclerView3 = onView(
                allOf(withId(R.id.recvFavorite),
                        childAtPosition(
                                withId(R.id.swipeRefresh),
                                0)))
        recyclerView3.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(isRoot()).perform(waitFor(3000))

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        onView(isRoot()).perform(waitFor(3000))

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

    }

    companion object {

        fun waitFor(millis: Long): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return isRoot()
                }

                override fun getDescription(): String {
                    return "Wait for $millis milliseconds."
                }

                override fun perform(uiController: UiController, view: View) {
                    uiController.loopMainThreadForAtLeast(millis)
                }
            }
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
                    return (parent is ViewGroup && parentMatcher.matches(parent)
                            && view == parent.getChildAt(position))
                }
            }
        }
    }
}
