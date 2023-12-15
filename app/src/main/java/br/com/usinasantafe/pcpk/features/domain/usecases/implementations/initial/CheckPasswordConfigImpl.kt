package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.CheckPasswordConfig
import javax.inject.Inject

class CheckPasswordConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository
): CheckPasswordConfig {

    override suspend fun invoke(senha: String): Boolean {
        return if (configRepository.hasConfig()) configRepository.getConfig().passwordConfig == senha else true
    }

}