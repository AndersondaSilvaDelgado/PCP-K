package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.utils.FlowApp
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface SetVeiculoVisitTerc {
    suspend operator fun invoke(veiculo: String, flowApp: FlowApp, pos: Int): Boolean
}

class SetVeiculoVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): SetVeiculoVisitTerc {

    override suspend fun invoke(veiculo: String, flowApp: FlowApp, pos: Int): Boolean {
        return try {
            when(flowApp){
                FlowApp.ADD -> movEquipVisitTercRepository.setVeiculoMovEquipVisitTerc(veiculo)
                FlowApp.CHANGE -> {
                    val movEquip = movEquipVisitTercRepository.listMovEquipVisitTercStarted()[pos]
                    movEquipVisitTercRepository.updateVeiculoMovEquipVisitTerc(veiculo, movEquip)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}