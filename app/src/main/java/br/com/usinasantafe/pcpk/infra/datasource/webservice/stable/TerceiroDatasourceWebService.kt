package br.com.usinasantafe.pcpk.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.TerceiroRoomModel
import kotlinx.coroutines.flow.Flow

interface TerceiroDatasourceWebService {

    suspend fun getAllTerceiro(nroAparelho: Long): Flow<Result<List<TerceiroRoomModel>>>

}