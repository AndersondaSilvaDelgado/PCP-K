package br.com.usinasantafe.pcpk.features.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface CloseSendAllMovVisitTerc {
    suspend operator fun invoke(): Boolean
}

class CloseSendAllMovVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val startProcessSendData: StartProcessSendData,
): CloseSendAllMovVisitTerc {

    override suspend fun invoke(): Boolean {
        try{
            var check = true
            val movEquipVisitTercList = movEquipVisitTercRepository.listMovEquipVisitTercStarted()
            for (movEquipVisitTerc in movEquipVisitTercList) {
                check = movEquipVisitTercRepository.setStatusSendMov(movEquipVisitTerc)
            }
            if(!check) return false
            setStatusSendConfig(StatusSend.SEND)
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }
}