package br.com.usinasantafe.pcpk.features.infra.datasource.webservice.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.EquipRoomModel
import kotlinx.coroutines.flow.Flow

interface EquipDatasourceWebService {

    suspend fun getAllEquip(nroAparelho: Long): Flow<Result<List<EquipRoomModel>>>

}