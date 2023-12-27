package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.CheckPasswordConfig
import javax.inject.Inject

class CheckPasswordConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository
): CheckPasswordConfig {

    override suspend fun invoke(senha: String): Boolean {
        if (!configRepository.hasConfig())
            return true
        if(configRepository.getConfig().passwordConfig != senha)
            return false
        return true
    }

}