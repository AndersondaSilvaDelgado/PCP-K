package br.com.usinasantafe.pcpk.infra.datasource.sharedpreferences

interface MovEquipProprioPassagDatasourceSharedPreferences {

    suspend fun addPassag(nroMatric: Long): Boolean

    suspend fun clearPassag(): Boolean

    suspend fun deletePassag(pos: Int): Boolean

    suspend fun listPassag(): List<Long>
}