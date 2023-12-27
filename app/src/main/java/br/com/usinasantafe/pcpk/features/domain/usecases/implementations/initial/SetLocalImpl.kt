package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.LocalRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.SetLocal
import javax.inject.Inject

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