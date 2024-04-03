package br.com.usinasantafe.pcpk.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.ColabRoomModel
import kotlinx.coroutines.flow.Flow

interface ColabDatasourceWebService {

    suspend fun getAllColab(nroAparelho: Long): Flow<Result<List<ColabRoomModel>>>

}