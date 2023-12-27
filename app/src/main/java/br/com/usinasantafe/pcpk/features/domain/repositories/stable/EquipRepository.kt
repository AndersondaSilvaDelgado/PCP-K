package br.com.usinasantafe.pcpk.features.domain.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Equip
import kotlinx.coroutines.flow.Flow

interface EquipRepository {

    suspend fun addAllEquip(list: List<Equip>)

    suspend fun deleteAllEquip()

    suspend fun recoverAllEquip(nroAparelho: Long): Flow<Result<List<Equip>>>

}