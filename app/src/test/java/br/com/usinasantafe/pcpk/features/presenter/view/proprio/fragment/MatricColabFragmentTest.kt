package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.testing.manifest.R.style.FragmentScenarioEmptyFragmentActivityTheme
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.features.presenter.view.initial.InitialActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.StringEndsWith.endsWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class MatricColabFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun testButtonOk() = runBlocking {
//        mock<MatricColabViewModel>()
        launchFragmentInHiltContainer<MatricColabFragment>{
            val bundle = Bundle()
            bundle.putInt(MatricColabFragment.TYPE_ADD_OCUPANTE_MATRIC_COLAB, TypeAddOcupante.ADDMOTORISTA.ordinal)
            bundle.putInt(MatricColabFragment.POS_MATRIC_COLAB, 0)
            this.parentFragmentManager.setFragmentResult(MatricColabFragment.REQUEST_KEY_MATRIC_COLAB, bundle)
            onView(withId(R.id.edit_text_padrao)).perform(typeText("19759"))
//            onView(withId(R.id.button_ok_padrao)).perform(click())
//            onView(withText(endsWith("MATRICULA DO VIGIA"))).check(matches(isDisplayed()))
        }
    }

}

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = FragmentScenarioEmptyFragmentActivityTheme,
    crossinline action: Fragment.() -> Unit = {}
) {
    val startActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            InitialActivity::class.java
        )
    ).putExtra(
        "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
        themeResId
    )

    ActivityScenario.launch<InitialActivity>(startActivityIntent).onActivity { activity ->
        val fragment: Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )
        fragment.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        fragment.action()
    }
}
