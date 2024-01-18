package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetDestinoVisitTerc
import javax.inject.Inject

class SetDestinoVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): SetDestinoVisitTerc {

    override suspend fun invoke(destino: String, flowApp: FlowApp, pos: Int): Boolean {
        return when(flowApp) {
            FlowApp.ADD -> movEquipVisitTercRepository.setDestinoMovEquipVisitTerc(destino)
            FlowApp.CHANGE -> {
                val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                movEquipVisitTercRepository.updateDestinoMovEquipVisitTerc(destino, movEquip)
            }
        }
    }

}