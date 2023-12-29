package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.features.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.stable.LocalRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.RecoverBase
import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModelOutput
import javax.inject.Inject

class RecoverBaseImpl @Inject constructor (
    private val configRepository: ConfigRepository,
    private val colabRepository: ColabRepository,
    private val localRepository: LocalRepository,
): RecoverBase {

    override suspend fun invoke(): ConfigModelOutput {
        val config = configRepository.getConfig()
        return ConfigModelOutput(
            nomeVigia = colabRepository.getColabMatric(config.matricVigia!!).nomeColab,
            local = localRepository.getLocalId(config.idLocal!!).descrLocal,
        )
    }

}