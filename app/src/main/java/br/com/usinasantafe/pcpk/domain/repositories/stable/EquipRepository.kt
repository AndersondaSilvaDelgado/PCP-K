package br.com.usinasantafe.pcpk.domain.repositories.stable

import br.com.usinasantafe.pcpk.domain.entities.stable.Colab
import br.com.usinasantafe.pcpk.domain.entities.stable.Equip
import kotlinx.coroutines.flow.Flow

interface EquipRepository {

    suspend fun addAllEquip(list: List<br.com.usinasantafe.pcpk.domain.entities.stable.Equip>)

    suspend fun checkEquipNro(nro: Long): Boolean

    suspend fun deleteAllEquip()

    suspend fun getEquipId(id: Long): br.com.usinasantafe.pcpk.domain.entities.stable.Equip

    suspend fun getEquipNro(nro: Long): br.com.usinasantafe.pcpk.domain.entities.stable.Equip

    suspend fun recoverAllEquip(nroAparelho: Long): Flow<Result<List<br.com.usinasantafe.pcpk.domain.entities.stable.Equip>>>

}