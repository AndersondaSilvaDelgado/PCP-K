package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import kotlinx.coroutines.flow.Flow

interface GetStatusSendConfig {

    suspend operator fun invoke(): Flow<StatusSend>

}