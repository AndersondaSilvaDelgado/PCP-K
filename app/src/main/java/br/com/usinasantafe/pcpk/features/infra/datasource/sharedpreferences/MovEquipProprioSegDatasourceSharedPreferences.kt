package br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioSeg


interface MovEquipProprioSegDatasourceSharedPreferences {

    suspend fun addEquipSeg(movEquipProprioSeg: MovEquipProprioSeg): Boolean

    suspend fun clearEquipSeg(): Boolean

    suspend fun deleteEquipSeg(pos: Int): Boolean

    suspend fun listEquipSeg(): List<MovEquipProprioSeg>
}