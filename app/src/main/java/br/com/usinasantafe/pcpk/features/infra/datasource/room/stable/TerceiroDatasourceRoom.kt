package br.com.usinasantafe.pcpk.features.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.TerceiroRoomModel

interface TerceiroDatasourceRoom {

    suspend fun addAllTerceiro(vararg terceiroRoomModels: TerceiroRoomModel)

    suspend fun deleteAllTerceiro()

}