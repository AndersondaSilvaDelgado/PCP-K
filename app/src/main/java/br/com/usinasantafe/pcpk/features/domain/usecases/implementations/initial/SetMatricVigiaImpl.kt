package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.SetMatricVigia
import javax.inject.Inject

class SetMatricVigiaImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): SetMatricVigia {
    override suspend fun invoke(matricVigia: String): Boolean {
        try {
            val config = configRepository.getConfig()
            config.matricVigia = matricVigia.toLong()
            configRepository.saveConfig(config)
        } catch (exception: Exception) {
            return false
        }
        return true
    }
}