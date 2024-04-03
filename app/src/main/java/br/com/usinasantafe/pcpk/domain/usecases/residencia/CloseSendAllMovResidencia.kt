package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.domain.usecases.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.domain.usecases.background.StartProcessSendData
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
            val movEquipList = movEquipResidenciaRepository.listMovEquipResidenciaStarted()
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