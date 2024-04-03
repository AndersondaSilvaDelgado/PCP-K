package br.com.usinasantafe.pcpk.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.infra.models.room.variable.MovEquipProprioRoomModel


interface MovEquipProprioDatasourceRoom {

    suspend fun checkMovSend(): Boolean

    suspend fun deleteMov(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean

    suspend fun lastIdMovStatusStarted(): Long

    suspend fun listMovEquipProprioStarted(): List<MovEquipProprioRoomModel>

    suspend fun listMovEquipProprioSend(): List<MovEquipProprioRoomModel>

    suspend fun listMovEquipProprioSent(): List<MovEquipProprioRoomModel>

    suspend fun saveMovEquipProprioOpen(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean

    suspend fun updateDestinoMovEquipProprio(
        destino: String,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean

    suspend fun updateIdEquipMovEquipProprio(
        idEquip: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean

    suspend fun updateNroColabMovEquipProprio(
        nroMatric: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean

    suspend fun updateNotaFiscalMovEquipProprio(
        notaFiscal: Long,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean

    suspend fun updateObservMovEquipProprio(
        observ: String?,
        movEquipProprioRoomModel: MovEquipProprioRoomModel
    ): Boolean

    suspend fun updateStatusMovEquipProprioCloseSend(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean

    suspend fun updateStatusMovEquipProprioSent(idMov: Long): Boolean

}