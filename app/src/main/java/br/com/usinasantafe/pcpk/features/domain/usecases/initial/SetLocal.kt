package br.com.usinasantafe.pcpk.features.domain.usecases.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.LocalRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import javax.inject.Inject

interface SetLocal {
    suspend operator fun invoke(pos: Int): Boolean
}

class SetLocalImpl @Inject constructor(
    private val configRepository: ConfigRepository,
    private val localRepository: LocalRepository,
): SetLocal {

    override suspend fun invoke(pos: Int): Boolean {
        try {
            val config = configRepository.getConfig()
            val local = localRepository.listAllLocal()[pos]
            config.idLocal = local.idLocal
            configRepository.saveConfig(config)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}