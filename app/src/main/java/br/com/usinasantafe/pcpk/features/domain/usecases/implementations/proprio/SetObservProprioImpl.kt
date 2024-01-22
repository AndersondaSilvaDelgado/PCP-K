package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SaveMovEquipProprioOpen
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetObservProprio
import javax.inject.Inject

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
                val movEquip = movEquipProprioRepository.listMovEquipProprioOpen()[pos]
                movEquipProprioRepository.updateObservMovEquipProprio(observ, movEquip)
            }
        }
    }

}