package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetStatusSendCloseMovVisitTerc
import javax.inject.Inject

class SetStatusSendCloseMovVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val startProcessSendData: StartProcessSendData,
): SetStatusSendCloseMovVisitTerc {

    override suspend fun invoke(pos: Int): Boolean {
        try {
            val mov = movEquipVisitTercRepository.listMovEquipVisitTercEmpty()[pos]
            if(!movEquipVisitTercRepository.setStatusSendClosedMov(mov)) return false
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}