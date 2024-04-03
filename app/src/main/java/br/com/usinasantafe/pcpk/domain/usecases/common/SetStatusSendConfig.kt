package br.com.usinasantafe.pcpk.domain.usecases.common

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.repositories.variable.ConfigRepository
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