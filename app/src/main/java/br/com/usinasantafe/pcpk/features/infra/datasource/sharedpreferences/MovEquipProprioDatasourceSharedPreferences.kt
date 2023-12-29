package br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio

interface MovEquipProprioDatasourceSharedPreferences {

    suspend fun getMovEquipProprio(): MovEquipProprio

    suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean

    suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean

}