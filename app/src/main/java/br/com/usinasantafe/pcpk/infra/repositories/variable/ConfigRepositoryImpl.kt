package br.com.usinasantafe.pcpk.infra.repositories.variable

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences.ConfigDatasourceSharedPreferences
import br.com.usinasantafe.pcpk.infra.datasource.webservice.variable.ConfigDatasourceWebService
import br.com.usinasantafe.pcpk.infra.models.webservice.toConfigWebServiceModel
import br.com.usinasantafe.pcpk.infra.models.webservice.toConfig
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

    override suspend fun getConfig(): br.com.usinasantafe.pcpk.domain.entities.variable.Config {
        return configDatasourceSharedPreferences.getConfig()
    }

    override suspend fun saveConfig(config: br.com.usinasantafe.pcpk.domain.entities.variable.Config) {
        configDatasourceSharedPreferences.saveConfig(config)
    }


    override suspend fun recoverToken(config: br.com.usinasantafe.pcpk.domain.entities.variable.Config): Flow<Result<br.com.usinasantafe.pcpk.domain.entities.variable.Config>> = flow {
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