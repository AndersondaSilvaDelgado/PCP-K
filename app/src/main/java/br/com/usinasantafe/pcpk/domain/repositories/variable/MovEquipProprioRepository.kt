package br.com.usinasantafe.pcpk.domain.repositories.variable

import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio

interface MovEquipProprioRepository {

    suspend fun checkAddMotoristaMovEquipProprio(): Boolean

    suspend fun checkAddVeiculoMovEquipProprio(): Boolean

    suspend fun checkMovSend(): Boolean

    suspend fun deleteMovEquipProprio(movEquipProprio: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio): Boolean

    suspend fun getMatricMotoristaMovEquipProprio(): Long

    suspend fun getTipoMovEquipProprio(): TypeMov

    suspend fun listMovEquipProprioStarted(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>

    suspend fun listMovEquipProprioSend(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>

    suspend fun listMovEquipProprioSent(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>

    suspend fun receiverSentMovEquipProprio(movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>): Boolean

    suspend fun saveMovEquipProprio(matricVigia: Long, idLocal: Long): Long

    suspend fun sendMovEquipProprio(
        movEquipList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>,
        nroAparelho: Long
    ): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>>

    suspend fun setDestinoMovEquipProprio(destino: String): Boolean

    suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean

    suspend fun setNotaFiscalMovEquipProprio(notaFiscal: Long): Boolean

    suspend fun setObservMovEquipProprio(observ: String?): Boolean

    suspend fun setStatusSendMov(movEquipProprio: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio): Boolean

    suspend fun setVeiculoMovEquipProprio(idEquip: Long): Boolean

    suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean

    suspend fun updateDestinoMovEquipProprio(
        destino: String,
        movEquipProprio: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
    ): Boolean

    suspend fun updateMotoristaMovEquipProprio(
        nroMatric: Long,
        movEquipProprio: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
    ): Boolean

    suspend fun updateNotaFiscalMovEquipProprio(
        notaFiscal: Long,
        movEquipProprio: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
    ): Boolean

    suspend fun updateVeiculoMovEquipProprio(
        idEquip: Long,
        movEquipProprio: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
    ): Boolean

    suspend fun updateObservMovEquipProprio(
        observ: String?,
        movEquipProprio: br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
    ): Boolean

}