package br.com.usinasantafe.pcpk.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.VisitanteRoomModel
import kotlinx.coroutines.flow.Flow

interface VisitanteDatasourceWebService {

    suspend fun getAllVisitante(nroAparelho: Long): Flow<Result<List<VisitanteRoomModel>>>

}