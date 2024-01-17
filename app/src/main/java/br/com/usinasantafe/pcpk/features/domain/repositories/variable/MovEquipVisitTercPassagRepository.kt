package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTercPassag

interface MovEquipVisitTercPassagRepository {

    suspend fun addPassag(nroMatric: Long): Boolean

    suspend fun deletePassag(pos: Int): Boolean

    suspend fun listPassag(): List<MovEquipVisitTercPassag>

    suspend fun listPassagIdMov(idMov: Long): List<MovEquipVisitTercPassag>

    suspend fun savePassag(idMov: Int): Boolean

    suspend fun savePassag(idMov: Int, passagList: List<MovEquipVisitTercPassag>): Boolean

}