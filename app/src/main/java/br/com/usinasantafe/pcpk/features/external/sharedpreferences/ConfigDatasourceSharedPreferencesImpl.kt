package br.com.usinasantafe.pcpk.features.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcpk.common.utils.BASE_SHARE_PREFERENCES_TABLE_CONFIG
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.ConfigDatasourceSharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class ConfigDatasourceSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ConfigDatasourceSharedPreferences {

    override suspend fun hasConfig(): Boolean {
        val result = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, null)
        return result != null
    }

    override suspend fun getConfig(): Config {
        val config = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, null)
        if(config.isNullOrEmpty())
            return Config()
        return Gson().fromJson(config, Config::class.java)
    }

    override suspend fun saveConfig(config: Config) {
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