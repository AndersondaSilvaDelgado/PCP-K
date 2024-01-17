package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.SetStatusSendConfig
import javax.inject.Inject

class SetStatusSendConfigImpl @Inject constructor(
    private val configRepository: ConfigRepository,
): SetStatusSendConfig {

    override suspend fun invoke(statusSend: StatusSend) {
        configRepository.setStatusSendConfig(statusSend)
    }

}