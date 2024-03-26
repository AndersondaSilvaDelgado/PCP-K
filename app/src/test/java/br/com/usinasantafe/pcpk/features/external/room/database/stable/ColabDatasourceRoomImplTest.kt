package br.com.usinasantafe.pcpk.features.external.room.database.stable

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.usinasantafe.pcpk.features.external.room.AppDatabaseRoom
import br.com.usinasantafe.pcpk.features.external.room.dao.stable.ColabDao
import br.com.usinasantafe.pcpk.features.infra.models.room.stable.ColabRoomModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ColabDatasourceRoomImplTest {

    private lateinit var colabDao: ColabDao
    private lateinit var db: AppDatabaseRoom

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabaseRoom::class.java).allowMainThreadQueries().build()
        colabDao = db.colabDao()
    }

    @After
    fun tearDown() {
        db.close()
    }


    @Test
    fun testAddColab() = runBlocking {
        val model = listOf(ColabRoomModel(10L, "Anderson"))
        var list = model.toTypedArray()
        colabDao.insertAll(*list)
        val result = colabDao.checkColabMatric(10L)
        assertEquals(result, 1)
    }
}