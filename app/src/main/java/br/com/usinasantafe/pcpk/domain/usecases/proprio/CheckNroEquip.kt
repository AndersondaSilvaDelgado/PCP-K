package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.domain.repositories.stable.EquipRepository
import javax.inject.Inject

interface CheckNroEquip {
    suspend operator fun invoke(nro: String): Boolean
}

class CheckNroEquipImpl @Inject constructor (
    private val equipRepository: EquipRepository,
): CheckNroEquip {

    override suspend fun invoke(nro: String): Boolean {
        return equipRepository.checkEquipNro(nro.toLong())
    }

}