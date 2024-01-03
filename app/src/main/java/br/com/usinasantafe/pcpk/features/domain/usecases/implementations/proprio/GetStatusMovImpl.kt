package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.GetStatusMov
import javax.inject.Inject

class GetStatusMovImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): GetStatusMov {

    override suspend fun invoke(): StatusMovEquipProprio {
        return configRepository.getConfig().statusMovEquipProprio!!
    }

}