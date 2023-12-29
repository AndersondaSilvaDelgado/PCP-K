package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.common.utils.TypeMov

interface MovEquipProprioRepository {

    suspend fun checkAddMotoristaMovEquipProprio(): Boolean


    suspend fun getMatricMotoristaMovEquipProprio(): Long


    suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean

    suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean

}