package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc


interface MovEquipResidenciaRepository {

    suspend fun checkMovSend(): Boolean

    suspend fun listMovEquipResidenciaOpen(): List<MovEquipResidencia>

    suspend fun listMovEquipResidenciaEmpty(): List<MovEquipResidencia>

    suspend fun listMovEquipResidenciaSend(): List<MovEquipResidencia>

    suspend fun receiverSentMovEquipResidencia(movEquipList: List<MovEquipResidencia>): Boolean

    suspend fun saveMovEquipResidencia(matricVigia: Long, idLocal: Long): Int

    suspend fun saveMovEquipResidencia(matricVigia: Long, idLocal: Long, movEquipResidencia: MovEquipResidencia): Int

    suspend fun sendMovEquipResidencia(movEquipList: List<MovEquipResidencia>, nroAparelho: Long): Result<List<MovEquipResidencia>>

    suspend fun setMotoristaMovEquipResidencia(motorista: String): Boolean

    suspend fun setObservMovEquipResidencia(observ: String?): Boolean

    suspend fun setPlacaMovEquipResidencia(placa: String): Boolean

    suspend fun setStatusClosedMov(movEquipResidencia: MovEquipResidencia): Boolean

    suspend fun setStatusSendClosedMov(movEquipResidencia: MovEquipResidencia): Boolean

    suspend fun setVeiculoMovEquipResidencia(veiculo: String): Boolean

    suspend fun startMovEquipResidencia(): Boolean

}