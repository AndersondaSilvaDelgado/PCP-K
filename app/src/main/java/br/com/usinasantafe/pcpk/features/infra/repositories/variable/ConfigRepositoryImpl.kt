package br.com.usinasantafe.pcpk.features.infra.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences.ConfigDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.ConfigDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.webservice.toConfigWebServiceModel
import br.com.usinasantafe.pcpk.features.infra.models.webservice.toConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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

    override suspend fun saveConfig(config: Config) {
        configDatasourceSharedPreferences.saveConfig(config)
    }


    override suspend fun recoverToken(config: Config): Flow<Result<Config>> = flow {
        configDatasourceWebService.recoverToken(config.toConfigWebServiceModel())
            .catch { exception -> emit(Result.failure(exception)) }
            .collect { result ->
                result.fold(
                    onSuccess = {
                        emit(Result.success(it.toConfig()))
                    },
                    onFailure = { exception -> emit(Result.failure(exception)) }
                )
            }
    }

    override suspend fun setStatusSendConfig(statusSend: StatusSend) {
        configDatasourceSharedPreferences.setStatusSend(statusSend)
    }

}