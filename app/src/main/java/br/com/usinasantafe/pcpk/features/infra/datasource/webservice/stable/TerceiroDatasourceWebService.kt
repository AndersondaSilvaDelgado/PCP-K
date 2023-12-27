package br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.TerceiroRoomModel
import kotlinx.coroutines.flow.Flow

interface TerceiroDatasourceWebService {

    suspend fun getAllTerceiro(nroAparelho: Long): Flow<Result<List<TerceiroRoomModel>>>

}