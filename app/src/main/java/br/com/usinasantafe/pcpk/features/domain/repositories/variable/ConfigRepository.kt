package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {

    suspend fun hasConfig(): Boolean

    suspend fun getConfig(): Config

    suspend fun saveConfig(config: Config)

    suspend fun recoverToken(config: Config): Flow<Result<Config>>

}