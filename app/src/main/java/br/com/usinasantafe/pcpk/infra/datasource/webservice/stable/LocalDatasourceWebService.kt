package br.com.usinasantafe.pcpk.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.LocalRoomModel
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceWebService {

    suspend fun getAllLocal(nroAparelho: Long): Flow<Result<List<LocalRoomModel>>>

}