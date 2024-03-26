package br.com.usinasantafe.pcpk.features.domain.usecases.initial

import br.com.usinasantafe.pcpk.common.utils.FlagUpdate
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import javax.inject.Inject

interface CheckAcessApont {
    suspend operator fun invoke(): Boolean
}

class CheckAcessApontImpl @Inject constructor(
    private val configRepository: ConfigRepository
): CheckAcessApont {

    override suspend fun invoke(): Boolean {
        if(configRepository.getConfig().flagUpdate == FlagUpdate.OUTDATED)
            return false
        return true
    }

}