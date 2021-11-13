package com.gfdellatin.coinconverter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.gfdellatin.coinconverter.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun shouldNotAbleOnMandatoryFieldConverter_whenValueFieldIsEmpty() {
        onView(withId(R.id.til_value)).perform(click())
        onView(withId(R.id.btn_converter)).check(matches(isNotEnabled()))
    }

    @Test
    fun shouldNotAbleOnMandatoryFieldSave_whenValueFieldIsEmpty() {
        onView(withId(R.id.til_value)).perform(click())
        onView(withId(R.id.btn_save)).check(matches(isNotEnabled()))
    }

}