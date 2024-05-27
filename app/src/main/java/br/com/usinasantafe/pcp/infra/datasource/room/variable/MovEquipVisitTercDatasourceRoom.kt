package br.com.usinasantafe.pcp.infra.datasource.room.variable

import br.com.usinasantafe.pcp.infra.models.room.variable.MovEquipVisitTercRoomModel


interface MovEquipVisitTercDatasourceRoom {

    suspend fun checkMovSend(): Boolean

    suspend fun deleteMovEquipVisitTerc(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

    suspend fun insertMovEquipVisitTercOpen(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

    suspend fun insertMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

    suspend fun lastIdMovStatusStarted(): Long

    suspend fun listMovEquipVisitTercOpen(): List<MovEquipVisitTercRoomModel>

    suspend fun listMovEquipVisitTercStarted(): List<MovEquipVisitTercRoomModel>

    suspend fun listMovEquipVisitTercStartedClosed(): List<MovEquipVisitTercRoomModel>

    suspend fun listMovEquipVisitTercSend(): List<MovEquipVisitTercRoomModel>

    suspend fun listMovEquipVisitTercSent(): List<MovEquipVisitTercRoomModel>

    suspend fun updateVeiculoMovEquipVisitTerc(
        veiculo: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean

    suspend fun updatePlacaMovEquipVisitTerc(
        placa: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean

    suspend fun updateMotoristaMovEquipVisitTerc(
        idVisitTerc: Long,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean

    suspend fun updateDestinoMovEquipVisitTerc(
        destino: String,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean

    suspend fun updateObservMovEquipVisitTerc(
        observ: String?,
        movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel
    ): Boolean

    suspend fun updateStatusMovEquipVisitTercSent(idMov: Long): Boolean

    suspend fun updateStatusMovEquipVisitTercClose(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

    suspend fun updateStatusMovEquipVisitTercCloseSend(movEquipVisitTercRoomModel: MovEquipVisitTercRoomModel): Boolean

}