package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.utils.TypeMov
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import javax.inject.Inject

interface GetTipoMov {
    suspend operator fun invoke(): TypeMov
}

class GetTipoMovImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): GetTipoMov {

    override suspend fun invoke(): TypeMov {
        return movEquipProprioRepository.getTipoMovEquipProprio()
    }

}