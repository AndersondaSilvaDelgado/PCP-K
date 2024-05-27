package br.com.usinasantafe.pcp.infra.datasource.webservice.stable

import br.com.usinasantafe.pcp.infra.models.room.stable.ColabRoomModel
import kotlinx.coroutines.flow.Flow

interface ColabDatasourceWebService {

    suspend fun getAllColab(token: String): Flow<Result<List<ColabRoomModel>>>

}