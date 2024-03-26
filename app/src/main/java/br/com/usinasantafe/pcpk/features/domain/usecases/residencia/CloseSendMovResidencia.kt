package br.com.usinasantafe.pcpk.features.domain.usecases.residencia

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface CloseSendMovResidencia {
    suspend operator fun invoke(pos: Int): Boolean
}

class CloseSendMovResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val startProcessSendData: StartProcessSendData,
): CloseSendMovResidencia {

    override suspend fun invoke(pos: Int): Boolean {
        try {
            val mov = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
            if(!movEquipResidenciaRepository.setStatusSendCloseMov(mov)) return false
            setStatusSendConfig(StatusSend.SEND)
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}