package br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.VisitanteRoomModel
import kotlinx.coroutines.flow.Flow

interface VisitanteDatasourceWebService {

    suspend fun getAllVisitante(nroAparelho: Long): Flow<Result<List<VisitanteRoomModel>>>

}