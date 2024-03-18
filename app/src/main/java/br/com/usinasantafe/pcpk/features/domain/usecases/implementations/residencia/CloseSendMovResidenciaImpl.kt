package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.CloseSendMovResidencia
import javax.inject.Inject

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