package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia


interface MovEquipResidenciaRepository {

    suspend fun checkMovSend(): Boolean

    suspend fun listMovEquipResidenciaOpen(): List<MovEquipResidencia>

    suspend fun listMovEquipResidenciaStarted(): List<MovEquipResidencia>

    suspend fun listMovEquipResidenciaSend(): List<MovEquipResidencia>

    suspend fun receiverSentMovEquipResidencia(movEquipList: List<MovEquipResidencia>): Boolean

    suspend fun saveMovEquipResidencia(matricVigia: Long, idLocal: Long): Int

    suspend fun saveMovEquipResidencia(matricVigia: Long, idLocal: Long, movEquipResidencia: MovEquipResidencia): Int

    suspend fun sendMovEquipResidencia(movEquipList: List<MovEquipResidencia>, nroAparelho: Long): Result<List<MovEquipResidencia>>

    suspend fun setMotoristaMovEquipResidencia(motorista: String): Boolean

    suspend fun setObservMovEquipResidencia(observ: String?): Boolean

    suspend fun setPlacaMovEquipResidencia(placa: String): Boolean

    suspend fun setStatusCloseMov(movEquipResidencia: MovEquipResidencia): Boolean

    suspend fun setStatusSendCloseMov(movEquipResidencia: MovEquipResidencia): Boolean

    suspend fun setVeiculoMovEquipResidencia(veiculo: String): Boolean

    suspend fun startMovEquipResidencia(): Boolean

    suspend fun updateVeiculoMovEquipResidencia(
        veiculo: String,
        movEquipResidencia: MovEquipResidencia
    ): Boolean

    suspend fun updatePlacaMovEquipResidencia(
        placa: String,
        movEquipResidencia: MovEquipResidencia
    ): Boolean

    suspend fun updateMotoristaMovEquipResidencia(
        motorista: String,
        movEquipResidencia: MovEquipResidencia
    ): Boolean

    suspend fun updateObservMovEquipResidencia(
        observ: String?,
        movEquipResidencia: MovEquipResidencia
    ): Boolean

}