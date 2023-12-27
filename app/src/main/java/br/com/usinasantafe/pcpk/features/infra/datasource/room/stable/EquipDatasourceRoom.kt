package br.com.usinasantafe.pcpk.features.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.EquipRoomModel

interface EquipDatasourceRoom {

    suspend fun addAllEquip(vararg equipRoomModels: EquipRoomModel)

    suspend fun deleteAllEquip()

}