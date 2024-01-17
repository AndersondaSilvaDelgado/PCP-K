package br.com.usinasantafe.pcpk.features.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipVisitTercRoomModel


interface MovEquipVisitTercDatasourceRoom {

    suspend fun checkMovSend(): Boolean

    suspend fun insertMovEquipVisitTercOpen(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

    suspend fun insertMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

    suspend fun lastIdMovStatusSend(): Int

    suspend fun listMovEquipVisitTercOpen(): List<MovEquipVisitTercRoomModel>

    suspend fun listMovEquipVisitTercEmpty(): List<MovEquipVisitTercRoomModel>

    suspend fun listMovEquipVisitTercSend(): List<MovEquipVisitTercRoomModel>

    suspend fun updateMovEquipVisitTercSent(idMov: Long): Boolean

    suspend fun updateMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

    suspend fun updateMovEquipVisitTercCloseSend(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

}