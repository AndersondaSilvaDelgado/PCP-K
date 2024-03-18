package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.CloseSendAllMovResidencia
import javax.inject.Inject

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