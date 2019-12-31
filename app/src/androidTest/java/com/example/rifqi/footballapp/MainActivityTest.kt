package com.example.rifqi.footballapp

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.rifqi.footballapp.utils.EspressoIdlingResource
import com.example.rifqi.footballapp.R.id.*
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun checkMatchSchedule() {

        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))

        onView(withId(rv_list_league))
            .check(matches(isDisplayed()))
        onView(withId(rv_list_league))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(rv_list_league))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
                    click()
                )
            )

        onView(withId(ib_facebook))
            .check(matches(isDisplayed()))

        onView(withId(ib_twitter))
            .check(matches(isDisplayed()))

        onView(withId(rv_categories))
            .check(matches(isDisplayed()))
        onView(withId(rv_categories))
        onView(withId(rv_categories))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(rv_prev_match))
            .check(matches(isDisplayed()))
        onView(withId(view_pager))
            .perform(swipeLeft())
        onView(withId(rv_next_match))
            .check(matches(isDisplayed()))
    }


    @Test
    fun checkDetailMatchAndAddToFavorite() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))

        onView(withId(rv_list_league))
            .check(matches(isDisplayed()))
        onView(withId(rv_list_league))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(rv_list_league))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    6,
                    click()
                )
            )

        onView(withId(ib_facebook))
            .check(matches(isDisplayed()))

        onView(withId(ib_twitter))
            .check(matches(isDisplayed()))

        onView(withId(rv_categories))
            .check(matches(isDisplayed()))
        onView(withId(rv_categories))
        onView(withId(rv_categories))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(rv_prev_match))
            .check(matches(isDisplayed()))
        onView(withId(rv_prev_match))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(withId(rv_prev_match))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    8,
                    click()
                )
            )

        onView(withId(root_detail))
            .check(matches(isDisplayed()))

        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
            .perform(click())

    }

    @Test
    fun searchMatchAndAddToFavorite() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))

        onView(withId(rv_list_league))
            .check(matches(isDisplayed()))
        onView(withId(rv_list_league))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(rv_list_league))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
                    click()
                )
            )

        onView(withId(ib_facebook))
            .check(matches(isDisplayed()))

        onView(withId(ib_twitter))
            .check(matches(isDisplayed()))

        onView(withId(rv_categories))
            .check(matches(isDisplayed()))
        onView(withId(rv_categories))
        onView(withId(rv_categories))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(action_search))
            .check(matches(isDisplayed()))
        onView(withId(action_search))
            .perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Man United"),
            pressImeActionButton()
        )

        onView(withId(rv_match_search))
            .check(matches(isDisplayed()))
        onView(withId(rv_match_search))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_match_search))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    click()
                )
            )
        onView(withId(root_detail))
            .check(matches(isDisplayed()))

        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
            .perform(click())
    }

    @Test
    fun deleteFavorite() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(favorites))
            .perform(click())

        onView(withId(rv_favorites_match))
            .check(matches(isDisplayed()))
        onView(withId(rv_favorites_match))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
            .perform(click())
    }
}