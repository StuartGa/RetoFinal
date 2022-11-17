package com.example.capstoneproject

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.capstoneproject.presentation.ui.view.CriptoListFragment
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class myTestSuite {

    private lateinit var scenario: FragmentScenario<CriptoListFragment>

    @Test
    fun testEventFragment() {
        val scenario = launchFragmentInContainer<CriptoListFragment>(
            initialState = Lifecycle.State.INITIALIZED
        )
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testNavigationListToDetail() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val titleScenario = launchFragmentInContainer<CriptoListFragment>()

        titleScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.action_criptoListFragment_to_cryptoDetailFragment)).perform(
            ViewActions.click()
        )
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.criptoDetailFragment)
    }

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Capstone_project)
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }
}
