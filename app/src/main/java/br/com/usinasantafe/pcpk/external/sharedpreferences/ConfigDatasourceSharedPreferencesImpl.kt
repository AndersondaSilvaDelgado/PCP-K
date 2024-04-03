package br.com.usinasantafe.pcpk.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.utils.BASE_SHARE_PREFERENCES_TABLE_CONFIG
import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences.ConfigDatasourceSharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class ConfigDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ConfigDatasourceSharedPreferences {

    override suspend fun hasConfig(): Boolean {
        val result = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, null)
        return result != null
    }

    override suspend fun getConfig(): br.com.usinasantafe.pcpk.domain.entities.variable.Config {
        val config = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, null)
        if(config.isNullOrEmpty())
            return br.com.usinasantafe.pcpk.domain.entities.variable.Config()
        return Gson().fromJson(config, br.com.usinasantafe.pcpk.domain.entities.variable.Config::class.java)
    }

    override suspend fun saveConfig(config: br.com.usinasantafe.pcpk.domain.entities.variable.Config) {
        val editor = sharedPreferences.edit()
        editor.putString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, Gson().toJson(config))
        editor.commit()
    }

    override suspend fun setStatusSend(statusSend: StatusSend) {
        val config = getConfig()
        config.statusEnvio = statusSend
        saveConfig(config)
    }

}