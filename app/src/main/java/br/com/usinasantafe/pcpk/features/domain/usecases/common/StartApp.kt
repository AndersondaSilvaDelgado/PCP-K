package br.com.usinasantafe.pcpk.features.domain.usecases.common

import br.com.usinasantafe.pcpk.common.utils.PointerStart
import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface StartApp {
    suspend operator fun invoke(): PointerStart
}

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