package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetStatusSendCloseMovResidencia
import javax.inject.Inject

class SetStatusSendCloseMovResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val startProcessSendData: StartProcessSendData,
): SetStatusSendCloseMovResidencia {

    override suspend fun invoke(pos: Int): Boolean {
        try {
            val mov = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
            if(!movEquipResidenciaRepository.setStatusSendCloseMov(mov)) return false
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}