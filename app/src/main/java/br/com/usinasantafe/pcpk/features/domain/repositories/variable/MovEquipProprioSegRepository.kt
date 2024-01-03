package br.com.usinasantafe.pcpk.features.domain.repositories.variable

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprioSeg


interface MovEquipProprioSegRepository {

    suspend fun addEquipSeg(movEquipProprioSeg: MovEquipProprioSeg): Boolean

    suspend fun clearEquipSeg(): Boolean

    suspend fun deleteEquipSeg(pos: Int): Boolean

    suspend fun listEquipSeg(): List<MovEquipProprioSeg>
}