package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetStatusMov
import javax.inject.Inject

class SetStatusMovImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): SetStatusMov {

    override suspend fun invoke(statusMovEquipProprio: StatusMovEquipProprio): Boolean {
        try {
            val config = configRepository.getConfig()
            config.statusMovEquipProprio = statusMovEquipProprio
            configRepository.saveConfig(config)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}