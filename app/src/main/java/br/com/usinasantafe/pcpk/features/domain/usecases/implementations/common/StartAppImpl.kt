package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import br.com.usinasantafe.pcpk.common.utils.PointerStart
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckMovOpen
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.DeleteMovSent
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartApp
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import javax.inject.Inject

class StartAppImpl @Inject constructor (
    private val startProcessSendData: StartProcessSendData,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val configRepository: ConfigRepository,
    private val checkMovOpen: CheckMovOpen,
    private val deleteMovSent: DeleteMovSent,
): StartApp {

    override suspend fun invoke(): PointerStart {
        if (!configRepository.hasConfig())
            setStatusSendConfig(StatusSend.SEND)
        deleteMovSent()
        startProcessSendData()
        if (checkMovOpen()) return PointerStart.MENUINICIAL
        return PointerStart.MENUAPONT
    }

}