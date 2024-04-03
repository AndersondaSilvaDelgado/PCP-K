package br.com.usinasantafe.pcpk.domain.repositories.variable

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.entities.variable.Config
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {

    suspend fun hasConfig(): Boolean

    suspend fun getConfig(): Config

    suspend fun saveConfig(config: Config)

    suspend fun recoverToken(config: Config): Flow<Result<Config>>

    suspend fun setStatusSendConfig(statusSend: StatusSend)

}