package br.com.usinasantafe.pcpk.features.external.webservices.datasource.variable

import br.com.usinasantafe.pcpk.features.external.webservices.api.variable.ConfigApi
import br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable.ConfigDatasourceWebService
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelOutput
import javax.inject.Inject

class ConfigDatasourceWebServiceImpl @Inject constructor(
    private val configApi: ConfigApi
): ConfigDatasourceWebService {

    override suspend fun recoverToken(config: ConfigWebServiceModelOutput): Result<ConfigWebServiceModelInput> {
        val response = configApi.send(config)
        if(!response.isSuccessful)
            return Result.failure(Throwable("Erro recebimento de dados"))
        return Result.success(response.body()!!)
    }
}