package br.com.usinasantafe.pcpk.features.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioPassagRoomModel

interface MovEquipProprioPassagDatasourceRoom {

    suspend fun addAllMovEquipProprioPassag(vararg movEquipProprioPassagRoomModels: MovEquipProprioPassagRoomModel): Boolean

    suspend fun addMovEquipProprioPassag(movEquipProprioPassagRoomModel: MovEquipProprioPassagRoomModel): Boolean

    suspend fun listMovEquipProprioPassagIdMov(idMov: Long): List<MovEquipProprioPassagRoomModel>

}