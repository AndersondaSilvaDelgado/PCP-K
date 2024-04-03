package br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences

interface MovEquipProprioSegDatasourceSharedPreferences {

    suspend fun addEquipSeg(idEquip: Long): Boolean

    suspend fun clearEquipSeg(): Boolean

    suspend fun deleteEquipSeg(pos: Int): Boolean

    suspend fun listEquipSeg(): List<Long>

}