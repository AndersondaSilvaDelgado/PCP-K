package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.GetTipoMov
import javax.inject.Inject

class GetTipoMovImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): GetTipoMov {

    override suspend fun invoke(): TypeMov {
        return movEquipProprioRepository.getTipoMovEquipProprio()
    }

}