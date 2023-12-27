package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.initial

import br.com.usinasantafe.pcpk.common.utils.FlagUpdate
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.CheckAcessApont
import javax.inject.Inject

class CheckAcessApontImpl @Inject constructor(
    private val configRepository: ConfigRepository
): CheckAcessApont {

    override suspend fun invoke(): Boolean {
        if(configRepository.getConfig().flagUpdate == FlagUpdate.OUTDATED)
            return false
        return true
    }

}