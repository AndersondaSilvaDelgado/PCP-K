package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverNomeVigia
import javax.inject.Inject

class RecoverNomeVigiaImpl @Inject constructor (
    private val configRepository: ConfigRepository,
    private val colabRepository: ColabRepository,
): RecoverNomeVigia {

    override suspend fun invoke(): String {
        val config = configRepository.getConfig()
        return colabRepository.getColabMatric(config.matricVigia!!).nomeColab
    }

}