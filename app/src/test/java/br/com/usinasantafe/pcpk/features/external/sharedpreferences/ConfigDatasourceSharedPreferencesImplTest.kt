package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.module.database.shared.SharedPreferencesModule
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConfigDatasourceSharedPreferencesImplTest {


    @Test
    fun testGetConfig() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val shared = context.getSharedPreferences("teste", Context.MODE_PRIVATE)
        val configDatasourceSharedPreferencesImpl = ConfigDatasourceSharedPreferencesImpl(shared)
        configDatasourceSharedPreferencesImpl.saveConfig(Config(11L))
        assertTrue(configDatasourceSharedPreferencesImpl.hasConfig())
    }
}