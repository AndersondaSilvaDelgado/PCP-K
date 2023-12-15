package br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config

interface ConfigDatasourceSharedPreferences {

    suspend fun hasConfig(): Boolean

    suspend fun getConfig(): Config

    suspend fun saveConfig(config: Config)
}