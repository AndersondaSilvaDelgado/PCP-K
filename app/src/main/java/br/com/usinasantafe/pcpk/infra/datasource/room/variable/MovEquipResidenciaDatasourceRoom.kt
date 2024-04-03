package br.com.usinasantafe.pcpk.infra.datasource.room.variable

import br.com.usinasantafe.pcpk.infra.models.room.variable.MovEquipResidenciaRoomModel

interface MovEquipResidenciaDatasourceRoom {

    suspend fun checkMovSend(): Boolean

    suspend fun deleteMovEquipResidencia(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

    suspend fun insertMovEquipResidenciaOpen(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

    suspend fun insertMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

    suspend fun lastIdMovStatusStarted(): Long

    suspend fun listMovEquipResidenciaOpen(): List<MovEquipResidenciaRoomModel>

    suspend fun listMovEquipResidenciaEmpty(): List<MovEquipResidenciaRoomModel>

    suspend fun listMovEquipResidenciaSend(): List<MovEquipResidenciaRoomModel>

    suspend fun listMovEquipResidenciaSent(): List<MovEquipResidenciaRoomModel>

    suspend fun updateVeiculoMovEquipResidencia(
        veiculo: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean

    suspend fun updatePlacaMovEquipResidencia(
        placa: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean

    suspend fun updateMotoristaMovEquipResidencia(
        motorista: String,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean

    suspend fun updateObservMovEquipResidencia(
        observ: String?,
        movEquipVisitTercRoomModel: MovEquipResidenciaRoomModel
    ): Boolean

    suspend fun updateStatusSentMovEquipResidencia(idMov: Long): Boolean

    suspend fun updateStatusMovEquipResidenciaClose(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

    suspend fun updateStatusSendCloseMovEquipResidencia(movEquipResidenciaRoomModel: MovEquipResidenciaRoomModel): Boolean

}