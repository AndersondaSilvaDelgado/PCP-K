package br.com.usinasantafe.pcpk.presentermodel.proprio

import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.usinasantafe.pcpk.domain.usecases.common.CheckMatricColab
import br.com.usinasantafe.pcpk.domain.usecases.database.UpdateColab
import br.com.usinasantafe.pcpk.presenter.proprio.matric.MatricColabFragmentState
import br.com.usinasantafe.pcpk.presenter.proprio.matric.MatricColabViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(MockitoJUnitRunner::class)
class MatricColabViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }
    @Test
    fun test() = runBlocking {
        val checkMatricColab = mock<CheckMatricColab>()
        val updateColab = mock<UpdateColab>()
        whenever(checkMatricColab("19759")).thenReturn(true)
        val viewModel = MatricColabViewModel(checkMatricColab, updateColab)
        viewModel.checkMatricColaborador("19759")
        assertEquals(viewModel.uiLiveData.value, MatricColabFragmentState.CheckMatric(true))
    }

    @Test
    fun testLiveData() = runBlocking {
        val checkMatricColab = mock<CheckMatricColab>()
        val updateColab = mock<UpdateColab>()
        whenever(checkMatricColab("19759")).thenReturn(true)
        val viewModel = MatricColabViewModel(checkMatricColab, updateColab)


        val observer = Observer<MatricColabFragmentState> {}
        try {

            viewModel.uiLiveData.observeForever(observer)

            viewModel.checkMatricColaborador("19759")

            val value = viewModel.uiLiveData.value
            assertEquals(value, MatricColabFragmentState.CheckMatric(true))

        } finally {
            // Whatever happens, don't forget to remove the observer!
            viewModel.uiLiveData.removeObserver(observer)
        }
    }

    @Test
    fun testLiveDataSimple() = runBlocking {
        val checkMatricColab = mock<CheckMatricColab>()
        val updateColab = mock<UpdateColab>()
        whenever(checkMatricColab("19759")).thenReturn(true)
        val viewModel = MatricColabViewModel(checkMatricColab, updateColab)
        viewModel.checkMatricColaborador("19759")
        val value = viewModel.uiLiveData.getOrAwaitValue()
        assertEquals(value, MatricColabFragmentState.CheckMatric(true))
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}