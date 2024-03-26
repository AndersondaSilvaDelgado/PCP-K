package br.com.usinasantafe.pcpk.features.domain.usecases.common

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import javax.inject.Inject

interface SetStatusSendConfig {
    suspend operator fun invoke(statusSend: StatusSend)
}

class SetStatusSendConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): SetStatusSendConfig {

    override suspend fun invoke(statusSend: StatusSend) {
        configRepository.setStatusSendConfig(statusSend)
    }

}