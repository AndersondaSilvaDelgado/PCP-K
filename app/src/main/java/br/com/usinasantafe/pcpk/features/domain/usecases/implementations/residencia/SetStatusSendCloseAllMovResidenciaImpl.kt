package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetStatusSendCloseAllMovResidencia
import javax.inject.Inject

class SetStatusSendCloseAllMovResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val startProcessSendData: StartProcessSendData,
): SetStatusSendCloseAllMovResidencia {

    override suspend fun invoke(): Boolean {
        try{
            val movEquipList = movEquipResidenciaRepository.listMovEquipResidenciaStarted()
            for (movEquip in movEquipList) {
                movEquipResidenciaRepository.setStatusSendCloseMov(movEquip)
            }
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}