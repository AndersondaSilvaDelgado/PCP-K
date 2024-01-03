package br.com.usinasantafe.pcpk.features.infra.datasource.room.stable

import br.com.usinasantafe.pcpk.features.infra.models.room.stable.EquipRoomModel

interface EquipDatasourceRoom {

    suspend fun addAllEquip(vararg equipRoomModels: EquipRoomModel)

    suspend fun deleteAllEquip()

    suspend fun checkEquipNro(nro: Long): Boolean

    suspend fun getEquipNro(nro: Long): EquipRoomModel

    suspend fun getEquipId(id: Long): EquipRoomModel

}