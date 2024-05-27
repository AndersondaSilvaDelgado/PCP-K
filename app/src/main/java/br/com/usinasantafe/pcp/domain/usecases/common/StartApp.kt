package br.com.usinasantafe.pcp.domain.usecases.common

import br.com.usinasantafe.pcp.BuildConfig
import br.com.usinasantafe.pcp.utils.PointerStart
import br.com.usinasantafe.pcp.utils.StatusSend
import br.com.usinasantafe.pcp.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcp.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface StartApp {
    suspend operator fun invoke(version: String): PointerStart
}

class StartAppImpl @Inject constructor(
    private val startProcessSendData: StartProcessSendData,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val configRepository: ConfigRepository,
    private val checkMovOpen: CheckMovOpen,
    private val deleteMovSent: DeleteMovSent,
) : StartApp {

    override suspend fun invoke(version: String): PointerStart {
        if (configRepository.hasConfig()) {
            configRepository.getConfig().version?.let { v ->
                if (v != version)
                    configRepository.clearAllDataConfig()
            }
        }
        if (!configRepository.hasConfig())
            setStatusSendConfig(StatusSend.SEND)
        deleteMovSent()
        startProcessSendData()
        if (checkMovOpen()) return PointerStart.MENUINICIAL
        return PointerStart.MENUAPONT
    }

}