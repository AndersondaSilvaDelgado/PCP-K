package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverConfig
import javax.inject.Inject

class RecoverConfigImpl @Inject constructor (
    private val configRepository: ConfigRepository
): RecoverConfig {
    override suspend fun invoke(): Config? {
        if (configRepository.hasConfig())
            return configRepository.getConfig()
        return null
    }
}