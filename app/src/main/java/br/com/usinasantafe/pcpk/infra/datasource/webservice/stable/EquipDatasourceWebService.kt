package br.com.usinasantafe.pcpk.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.infra.models.room.stable.EquipRoomModel
import kotlinx.coroutines.flow.Flow

interface EquipDatasourceWebService {

    suspend fun getAllEquip(nroAparelho: Long): Flow<Result<List<EquipRoomModel>>>

}