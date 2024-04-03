package br.com.usinasantafe.pcpk.domain.usecases.background

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestWorkerBuilder
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import androidx.test.espresso.matcher.ViewMatchers.assertThat

@RunWith(AndroidJUnit4::class)
class SleepWorkerTest {
    private lateinit var context: Context
    private lateinit var executor: Executor

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        executor = Executors.newSingleThreadExecutor()
    }

    @Test
    fun testSleepWorker() {
        val worker = TestWorkerBuilder<SleepWorker>(
            context = context,
            executor = executor
        ).build()

        val result = worker.doWork()
        assertThat(result, `is`(ListenableWorker.Result.success()))
    }
}