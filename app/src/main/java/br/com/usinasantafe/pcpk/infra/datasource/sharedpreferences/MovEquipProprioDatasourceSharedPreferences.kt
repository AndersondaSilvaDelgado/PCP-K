package br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences

import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.infra.models.sharedpreferences.MovEquipProprioSharedPreferencesModel

interface MovEquipProprioDatasourceSharedPreferences {

    suspend fun clearMovEquipProprio(): Boolean

    suspend fun getMovEquipProprio(): MovEquipProprioSharedPreferencesModel

    suspend fun setDestinoMovEquipProprio(destino: String): Boolean

    suspend fun setMotoristaMovEquipProprio(nroMatric: Long): Boolean

    suspend fun setNotaFiscalMovEquipProprio(notaFiscal: Long): Boolean

    suspend fun setObservMovEquipProprio(observ: String?): Boolean

    suspend fun setVeiculoMovEquipProprio(idEquip: Long): Boolean

    suspend fun startMovEquipProprio(typeMov: TypeMov): Boolean

}