package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.ConfigDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.ConfigDatasourceWebService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor (
    private val configDatasourceSharedPreferences: ConfigDatasourceSharedPreferences,
    private val configDatasourceWebService: ConfigDatasourceWebService,
) : ConfigRepository {

    override suspend fun hasConfig(): Boolean {
        return configDatasourceSharedPreferences.hasConfig()
    }

    override suspend fun getConfig(): Config {
        return configDatasourceSharedPreferences.getConfig()
    }

    override suspend fun recoverToken(nroEquip: String): Flow<Result<Config>> {
        var config = Config()
        return flow {
            configDatasourceWebService.recoverToken(config)
                .collect { result ->
                    result.onSuccess { equipModelList ->
                        emit(Result.success(equipModelList.map { it.toEquip() }))
                    }
                }
        }
    }

}