package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.RecoverNomeColab
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverNomeVigia
import javax.inject.Inject

class RecoverNomeVigiaImpl @Inject constructor (
    private val configRepository: ConfigRepository,
    private val recoverNomeColab: RecoverNomeColab,
): RecoverNomeVigia {

    override suspend fun invoke(): String {
        val config = configRepository.getConfig()
        return recoverNomeColab(config.matricVigia!!)
    }

}