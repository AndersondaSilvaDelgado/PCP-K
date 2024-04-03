package br.com.usinasantafe.pcpk.infra.datasource.webservice.variable

import br.com.usinasantafe.pcpk.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.infra.models.webservice.ConfigWebServiceModelInput
import br.com.usinasantafe.pcpk.infra.models.webservice.ConfigWebServiceModelOutput
import kotlinx.coroutines.flow.Flow

interface ConfigDatasourceWebService {

    suspend fun recoverToken(config: ConfigWebServiceModelOutput): Flow<Result<ConfigWebServiceModelInput>>

}