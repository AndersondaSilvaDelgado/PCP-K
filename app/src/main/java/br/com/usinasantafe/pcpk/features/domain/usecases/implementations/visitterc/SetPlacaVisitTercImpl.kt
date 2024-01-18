package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetPlacaVisitTerc
import javax.inject.Inject

class SetPlacaVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): SetPlacaVisitTerc {

    override suspend fun invoke(placa: String, flowApp: FlowApp, pos: Int): Boolean {
        return try {
            when(flowApp){
                FlowApp.ADD -> movEquipVisitTercRepository.setPlacaMovEquipVisitTerc(placa)
                FlowApp.CHANGE -> {
                    val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                    movEquipVisitTercRepository.updatePlacaMovEquipVisitTerc(placa, movEquip)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}