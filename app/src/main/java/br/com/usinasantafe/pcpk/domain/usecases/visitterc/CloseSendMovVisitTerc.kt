package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.domain.usecases.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface CloseSendMovVisitTerc {
    suspend operator fun invoke(pos: Int): Boolean
}

class CloseSendMovVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val startProcessSendData: StartProcessSendData,
): CloseSendMovVisitTerc {

    override suspend fun invoke(pos: Int): Boolean {
        try {
            val mov = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
            if(!movEquipVisitTercRepository.setStatusSendMov(mov)) return false
            setStatusSendConfig(StatusSend.SEND)
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}