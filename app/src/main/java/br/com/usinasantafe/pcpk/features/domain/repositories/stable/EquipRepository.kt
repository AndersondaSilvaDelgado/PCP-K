package br.com.usinasantafe.pcpk.features.domain.repositories.stable

import br.com.usinasantafe.pcpk.features.domain.entities.stable.Colab
import br.com.usinasantafe.pcpk.features.domain.entities.stable.Equip
import kotlinx.coroutines.flow.Flow

interface EquipRepository {

    suspend fun addAllEquip(list: List<Equip>)

    suspend fun checkEquipNro(nro: Long): Boolean

    suspend fun deleteAllEquip()

    suspend fun getEquipId(id: Long): Equip

    suspend fun getEquipNro(nro: Long): Equip

    suspend fun recoverAllEquip(nroAparelho: Long): Flow<Result<List<Equip>>>

}