package br.com.usinasantafe.pcp.domain.usecases.residencia

import br.com.usinasantafe.pcp.utils.StatusSend
import br.com.usinasantafe.pcp.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcp.domain.usecases.common.SetStatusSendConfig
import br.com.usinasantafe.pcp.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface CloseSendAllMovResidencia {
    suspend operator fun invoke(): Boolean
}

class CloseSendAllMovResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val startProcessSendData: StartProcessSendData,
): CloseSendAllMovResidencia {

    override suspend fun invoke(): Boolean {
        try{
            var check = true
            val movEquipList = movEquipResidenciaRepository.listMovEquipResidenciaStartedClosed()
            for (movEquip in movEquipList) {
                check = movEquipResidenciaRepository.setStatusSendCloseMov(movEquip)
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