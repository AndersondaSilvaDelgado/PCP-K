package br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.ColabRoomModel
import kotlinx.coroutines.flow.Flow

interface ColabDatasourceWebService {

    suspend fun getAllColab(nroAparelho: Long): Flow<Result<List<ColabRoomModel>>>

}