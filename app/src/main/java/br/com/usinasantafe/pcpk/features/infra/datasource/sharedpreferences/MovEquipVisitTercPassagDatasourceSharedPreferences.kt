package br.com.usinasantafe.pcpk.features.infra.datasource.sharedpreferences

interface MovEquipVisitTercPassagDatasourceSharedPreferences {

    suspend fun addPassag(idVisitTerc: Long): Boolean

    suspend fun clearPassag(): Boolean

    suspend fun deletePassag(pos: Int): Boolean

    suspend fun listPassag(): List<Long>
}