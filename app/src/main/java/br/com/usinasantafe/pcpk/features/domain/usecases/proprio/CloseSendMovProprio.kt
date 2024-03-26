package br.com.usinasantafe.pcpk.features.domain.usecases.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface CloseSendMovProprio {
    suspend operator fun invoke(pos: Int): Boolean
}

class CloseSendMovProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val startProcessSendData: StartProcessSendData,
): CloseSendMovProprio {

    override suspend fun invoke(pos: Int): Boolean {
        try {
            val mov = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
            if(!movEquipProprioRepository.setStatusSendMov(mov)) return false
            setStatusSendConfig(StatusSend.SEND)
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}