package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverConfig
import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModelOutput
import br.com.usinasantafe.pcpk.features.presenter.model.toConfigModel
import javax.inject.Inject

class RecoverConfigImpl @Inject constructor (
    private val configRepository: ConfigRepository
): RecoverConfig {

    override suspend fun invoke(): ConfigModelOutput? {
        if (configRepository.hasConfig())
            return configRepository.getConfig().toConfigModel()
        return null
    }

}