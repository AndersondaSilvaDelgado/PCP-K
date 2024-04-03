package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.utils.FlowApp
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface SetDestinoVisitTerc {
    suspend operator fun invoke(destino: String, flowApp: FlowApp, pos: Int): Boolean
}

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