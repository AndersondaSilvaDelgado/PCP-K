package br.com.usinasantafe.pcpk.features.external.webservices.datasource.variable

import br.com.usinasantafe.pcpk.features.external.webservices.api.variable.ConfigApi
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.ConfigDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConfigDatasourceWebServiceImpl @Inject constructor(
    private val configApi: ConfigApi
): ConfigDatasourceWebService {

    override suspend fun recoverToken(config: ConfigWebServiceModelOutput) : Flow<Result<ConfigWebServiceModelInput>> = flow {
            try {
                val response = configApi.send(config)
                if(!response.isSuccessful)
                    emit(Result.failure(Throwable("Erro recebimento de dados")))
                emit(Result.success(response.body()!!))
            } catch (exception: Exception) {
                emit(Result.failure(exception))
            }
    }
}