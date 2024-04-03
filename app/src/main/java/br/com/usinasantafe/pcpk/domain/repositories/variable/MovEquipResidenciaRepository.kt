package br.com.usinasantafe.pcpk.domain.repositories.variable

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia


interface MovEquipResidenciaRepository {

    suspend fun checkMovSend(): Boolean

    suspend fun deleteMovEquipResidencia(movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia): Boolean

    suspend fun listMovEquipResidenciaOpen(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>

    suspend fun listMovEquipResidenciaStarted(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>

    suspend fun listMovEquipResidenciaSend(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>

    suspend fun listMovEquipResidenciaSent(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>

    suspend fun receiverSentMovEquipResidencia(movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>): Boolean

    suspend fun saveMovEquipResidencia(matricVigia: Long, idLocal: Long): Long

    suspend fun saveMovEquipResidencia(matricVigia: Long, idLocal: Long, movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia): Long

    suspend fun sendMovEquipResidencia(movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>, nroAparelho: Long): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>>

    suspend fun setMotoristaMovEquipResidencia(motorista: String): Boolean

    suspend fun setObservMovEquipResidencia(observ: String?): Boolean

    suspend fun setPlacaMovEquipResidencia(placa: String): Boolean

    suspend fun setStatusCloseMov(movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia): Boolean

    suspend fun setStatusSendCloseMov(movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia): Boolean

    suspend fun setVeiculoMovEquipResidencia(veiculo: String): Boolean

    suspend fun startMovEquipResidencia(): Boolean

    suspend fun updateVeiculoMovEquipResidencia(
        veiculo: String,
        movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
    ): Boolean

    suspend fun updatePlacaMovEquipResidencia(
        placa: String,
        movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
    ): Boolean

    suspend fun updateMotoristaMovEquipResidencia(
        motorista: String,
        movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
    ): Boolean

    suspend fun updateObservMovEquipResidencia(
        observ: String?,
        movEquipResidencia: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
    ): Boolean

}