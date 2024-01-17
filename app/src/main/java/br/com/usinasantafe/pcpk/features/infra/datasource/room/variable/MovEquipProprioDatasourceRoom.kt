package br.com.usinasantafe.pcpk.features.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.features.infra.models.room.variable.MovEquipProprioRoomModel


interface MovEquipProprioDatasourceRoom {

    suspend fun checkMovSend(): Boolean

    suspend fun closeSendMov(movEquipProprioRoomModel: MovEquipProprioRoomModel): Boolean

    suspend fun lastIdMovStatusSend(): Int

    suspend fun listMovEquipProprioOpen(): List<MovEquipProprioRoomModel>

    suspend fun listMovEquipProprioEmpty(): List<MovEquipProprioRoomModel>

    suspend fun listMovEquipProprioSend(): List<MovEquipProprioRoomModel>

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

    suspend fun updateStatusMovEquipProprioSent(idMov: Long): Boolean

}