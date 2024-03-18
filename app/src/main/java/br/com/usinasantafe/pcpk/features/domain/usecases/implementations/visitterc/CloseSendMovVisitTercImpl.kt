package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.CloseSendMovVisitTerc
import javax.inject.Inject

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