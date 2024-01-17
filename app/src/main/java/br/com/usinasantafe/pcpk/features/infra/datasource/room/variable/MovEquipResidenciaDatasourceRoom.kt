package br.com.usinasantafe.pcpk.features.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipResidenciaRoomModel

interface MovEquipResidenciaDatasourceRoom {

    suspend fun checkMovSend(): Boolean

    suspend fun insertMovEquipResidenciaOpen(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

    suspend fun insertMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

    suspend fun lastIdMovStatusSend(): Int

    suspend fun listMovEquipResidenciaOpen(): List<MovEquipResidenciaRoomModel>

    suspend fun listMovEquipResidenciaEmpty(): List<MovEquipResidenciaRoomModel>

    suspend fun listMovEquipResidenciaSend(): List<MovEquipResidenciaRoomModel>

    suspend fun updateMovEquipResidenciaSent(idMov: Long): Boolean

    suspend fun updateMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

    suspend fun updateMovEquipResidenciaSendClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

}