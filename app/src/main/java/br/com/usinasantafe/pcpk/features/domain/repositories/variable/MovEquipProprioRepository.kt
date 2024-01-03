package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio

interface MovEquipProprioRepository {

    suspend fun checkAddMotoristaMovEquipProprio(): Boolean

    suspend fun checkAddVeiculoMovEquipProprio(): Boolean

    suspend fun getMatricMotoristaMovEquipProprio(): Long

    suspend fun getTipoMovMovEquipProprio(): TypeMov

    suspend fun listMovEquipProprioOpen(): List<MovEquipProprio>

    suspend fun setDestinoMovEquipProprio(destino: String): Boolean

    suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean

    suspend fun setNotaFiscalMovEquipProprio(notaFiscal: Long): Boolean

    suspend fun setObservMovEquipProprio(observ: String): Boolean

    suspend fun setVeiculoMovEquipProprio(idEquip: Long): Boolean

    suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean

}