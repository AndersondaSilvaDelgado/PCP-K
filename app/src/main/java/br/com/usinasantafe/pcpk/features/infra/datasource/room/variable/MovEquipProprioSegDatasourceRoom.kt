package br.com.usinasantafe.pcpk.features.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioSegRoomModel

interface MovEquipProprioSegDatasourceRoom {

    suspend fun addAllMovEquipProprioSeg(vararg movEquipProprioSegRoomModels: MovEquipProprioSegRoomModel): Boolean

    suspend fun addMovEquipProprioSeg(movEquipProprioSegRoomModel: MovEquipProprioSegRoomModel): Boolean

    suspend fun listMovEquipProprioSegIdMov(idMov: Long): List<MovEquipProprioSegRoomModel>

}