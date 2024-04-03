package br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.entities.variable.Config

interface ConfigDatasourceSharedPreferences {

    suspend fun hasConfig(): Boolean

    suspend fun getConfig(): br.com.usinasantafe.pcpk.domain.entities.variable.Config

    suspend fun saveConfig(config: br.com.usinasantafe.pcpk.domain.entities.variable.Config)

    suspend fun setStatusSend(statusSend: StatusSend)

}