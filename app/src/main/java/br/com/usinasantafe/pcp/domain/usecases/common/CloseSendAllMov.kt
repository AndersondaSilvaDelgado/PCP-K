package br.com.usinasantafe.pcp.domain.usecases.common

import br.com.usinasantafe.pcp.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcp.utils.StatusSend
import br.com.usinasantafe.pcp.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcp.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcp.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcp.utils.StatusData
import javax.inject.Inject

interface CloseSendAllMov {
    suspend operator fun invoke(): Boolean
}

class CloseSendAllMovImpl @Inject constructor(
    private val configRepository: ConfigRepository,
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
            val movEquipVisitTercList = movEquipVisitTercRepository.listMovEquipVisitTercStartedClosed()
            for (movEquipVisitTerc in movEquipVisitTercList) {
                check = movEquipVisitTercRepository.setStatusSendMov(movEquipVisitTerc)
            }
            val movEquipList = movEquipResidenciaRepository.listMovEquipResidenciaStartedClosed()
            for (movEquip in movEquipList) {
                check = movEquipResidenciaRepository.setStatusSendCloseMov(movEquip)
            }
            if(!check) return false
            val config = configRepository.getConfig()
            config.statusApont = StatusData.CLOSE
            configRepository.saveConfig(config)
            setStatusSendConfig(StatusSend.SEND)
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}