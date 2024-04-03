package br.com.usinasantafe.pcpk.domain.usecases.common

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.repositories.variable.ConfigRepository
import javax.inject.Inject

interface CheckStatusSend {
    suspend operator fun invoke(): Boolean
}

class CheckStatusSendImpl @Inject constructor (
    private val configRepository: ConfigRepository,
): CheckStatusSend {

    override suspend fun invoke(): Boolean {
        try {
            if(configRepository.getConfig().statusEnvio == StatusSend.SENT) return false
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}