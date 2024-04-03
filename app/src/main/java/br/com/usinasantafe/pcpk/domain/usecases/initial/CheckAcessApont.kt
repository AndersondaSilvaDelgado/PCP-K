package br.com.usinasantafe.pcpk.domain.usecases.initial

import br.com.usinasantafe.pcpk.utils.FlagUpdate
import br.com.usinasantafe.pcpk.domain.repositories.variable.ConfigRepository
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