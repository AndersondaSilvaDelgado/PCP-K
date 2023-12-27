package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.common.utils.FlagUpdate
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.SetCheckUpdateBDConfig
import javax.inject.Inject

class SetCheckUpdateBDConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): SetCheckUpdateBDConfig {

    override suspend fun invoke(flagUpdate: FlagUpdate) {
        val config = configRepository.getConfig()
        config.flagUpdate = flagUpdate
        configRepository.saveConfig(config)
    }

}