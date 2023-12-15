package br.com.usinasantafe.pcpk.features.infra.datasource.webservice.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcpk.features.infra.models.webservice.ConfigWebServiceModelOutput
import kotlinx.coroutines.flow.Flow

interface ConfigDatasourceWebService {
    suspend fun recoverToken(config: ConfigWebServiceModelOutput): Flow<Result<ConfigWebServiceModelInput>>

}