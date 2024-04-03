package br.com.usinasantafe.pcpk.domain.repositories.variable

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg


interface MovEquipProprioSegRepository {

    suspend fun addEquipSeg(idEquip: Long): Boolean

    suspend fun addEquipSeg(idEquip: Long, idMov: Long): Boolean

    suspend fun clearEquipSeg(): Boolean

    suspend fun deleteEquipSeg(pos: Int): Boolean

    suspend fun deleteEquipSeg(pos: Int, idMov: Long): Boolean

    suspend fun deleteEquipSeg(idMov: Long): Boolean

    suspend fun listEquipSeg(): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg>

    suspend fun listEquipSeg(idMov: Long): List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprioSeg>

    suspend fun saveEquipSeg(idMov: Long): Boolean

}