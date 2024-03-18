package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.GetStatusSendConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStatusSendConfigImpl @Inject constructor (
    private val configRepository: ConfigRepository,
): GetStatusSendConfig {

    override suspend fun invoke(): Flow<StatusSend> = flow {
        emit(configRepository.getConfig().statusEnvio)
    }

}