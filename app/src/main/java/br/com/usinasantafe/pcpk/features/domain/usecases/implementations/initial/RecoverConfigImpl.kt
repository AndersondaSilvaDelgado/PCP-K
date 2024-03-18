package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverConfig
import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModel
import br.com.usinasantafe.pcpk.features.presenter.model.toConfigModel
import javax.inject.Inject

class RecoverConfigImpl @Inject constructor (
    private val configRepository: ConfigRepository
): RecoverConfig {

    override suspend fun invoke(): ConfigModel? {
        if (!configRepository.hasConfig())
            return null
        if(configRepository.getConfig().passwordConfig.isNullOrEmpty())
            return null
        return configRepository.getConfig().toConfigModel()
    }

}