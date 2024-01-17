package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.CheckNroEquip
import javax.inject.Inject

class CheckNroEquipImpl @Inject constructor (
    private val equipRepository: EquipRepository,
): CheckNroEquip {

    override suspend fun invoke(nro: String): Boolean {
        return equipRepository.checkEquipNro(nro.toLong())
    }

}