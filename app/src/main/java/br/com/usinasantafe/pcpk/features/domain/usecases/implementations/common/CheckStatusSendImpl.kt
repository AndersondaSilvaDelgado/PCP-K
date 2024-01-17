package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckStatusSend
import javax.inject.Inject

class CheckStatusSendImpl @Inject constructor (
    private val configRepository: ConfigRepository,
): CheckStatusSend {

    override suspend fun invoke(): Boolean {
        try {
            if(configRepository.getConfig().statusEnvio != StatusSend.SENT) {
                return false
            }
            configRepository.setStatusSendConfig(StatusSend.SEND)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}