package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetStatusSendCloseAllMovVisitTerc
import javax.inject.Inject

class SetStatusSendCloseAllMovVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val startProcessSendData: StartProcessSendData,
): SetStatusSendCloseAllMovVisitTerc {

    override suspend fun invoke(): Boolean {
        try{
            val movEquipVisitTercList = movEquipVisitTercRepository.listMovEquipVisitTercStarted()
            for (movEquipVisitTerc in movEquipVisitTercList) {
                movEquipVisitTercRepository.setStatusSendCloseMov(movEquipVisitTerc)
            }
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }
}