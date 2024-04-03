package br.com.usinasantafe.pcpk.domain.usecases.common

import br.com.usinasantafe.pcpk.utils.StatusSend
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface CloseSendAllMov {
    suspend operator fun invoke(): Boolean
}

class CloseSendAllMovImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val setStatusSendConfig: SetStatusSendConfig,
): CloseSendAllMov {

    override suspend fun invoke(): Boolean {
        try {
            var check = true
            val movEquipProprioList = movEquipProprioRepository.listMovEquipProprioStarted()
            for (movEquipProprio in movEquipProprioList) {
                check = movEquipProprioRepository.setStatusSendMov(movEquipProprio)
            }
            val movEquipVisitTercList = movEquipVisitTercRepository.listMovEquipVisitTercStarted()
            for (movEquipVisitTerc in movEquipVisitTercList) {
                check = movEquipVisitTercRepository.setStatusSendMov(movEquipVisitTerc)
            }
            val movEquipList = movEquipResidenciaRepository.listMovEquipResidenciaStarted()
            for (movEquip in movEquipList) {
                check = movEquipResidenciaRepository.setStatusSendCloseMov(movEquip)
            }
            if(!check) return false
            setStatusSendConfig(StatusSend.SEND)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}