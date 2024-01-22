package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio

interface MovEquipProprioRepository {

    suspend fun checkAddMotoristaMovEquipProprio(): Boolean

    suspend fun checkAddVeiculoMovEquipProprio(): Boolean

    suspend fun checkMovSend(): Boolean

    suspend fun getMatricMotoristaMovEquipProprio(): Long

    suspend fun getTipoMovEquipProprio(): TypeMov

    suspend fun listMovEquipProprioOpen(): List<MovEquipProprio>

    suspend fun listMovEquipProprioEmpty(): List<MovEquipProprio>

    suspend fun listMovEquipProprioSend(): List<MovEquipProprio>

    suspend fun receiverSentMovEquipProprio(movEquipList: List<MovEquipProprio>): Boolean

    suspend fun saveMovEquipProprio(matricVigia: Long, idLocal: Long): Long

    suspend fun sendMovEquipProprio(
        movEquipList: List<MovEquipProprio>,
        nroAparelho: Long
    ): Result<List<MovEquipProprio>>

    suspend fun setDestinoMovEquipProprio(destino: String): Boolean

    suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean

    suspend fun setNotaFiscalMovEquipProprio(notaFiscal: Long): Boolean

    suspend fun setObservMovEquipProprio(observ: String?): Boolean

    suspend fun setStatusSendClosedMov(movEquipProprio: MovEquipProprio): Boolean

    suspend fun setVeiculoMovEquipProprio(idEquip: Long): Boolean

    suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean

    suspend fun updateDestinoMovEquipProprio(
        destino: String,
        movEquipProprio: MovEquipProprio
    ): Boolean

    suspend fun updateMotoristaMovEquipProprio(
        nroMatric: Long,
        movEquipProprio: MovEquipProprio
    ): Boolean

    suspend fun updateNotaFiscalMovEquipProprio(
        notaFiscal: Long,
        movEquipProprio: MovEquipProprio
    ): Boolean

    suspend fun updateVeiculoMovEquipProprio(
        idEquip: Long,
        movEquipProprio: MovEquipProprio
    ): Boolean

    suspend fun updateObservMovEquipProprio(
        observ: String?,
        movEquipProprio: MovEquipProprio
    ): Boolean

}