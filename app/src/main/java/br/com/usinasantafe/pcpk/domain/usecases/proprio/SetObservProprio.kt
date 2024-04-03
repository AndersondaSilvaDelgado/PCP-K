package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.utils.FlowApp
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import javax.inject.Inject

interface SetObservProprio {
    suspend operator fun invoke(observ: String?, flowApp: FlowApp, pos: Int): Boolean
}

class SetObservProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val saveMovEquipProprioOpen: SaveMovEquipProprioOpen,
): SetObservProprio {

    override suspend fun invoke(observ: String?, flowApp: FlowApp, pos: Int): Boolean {
        return when(flowApp) {
            FlowApp.ADD -> {
                if(!movEquipProprioRepository.setObservMovEquipProprio(observ)) return false
                return saveMovEquipProprioOpen()
            }
            FlowApp.CHANGE -> {
                val movEquip = movEquipProprioRepository.listMovEquipProprioStarted()[pos]
                movEquipProprioRepository.updateObservMovEquipProprio(observ, movEquip)
            }
        }
    }

}