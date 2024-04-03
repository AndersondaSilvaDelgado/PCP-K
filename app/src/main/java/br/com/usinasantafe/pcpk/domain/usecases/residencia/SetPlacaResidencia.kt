package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.utils.FlowApp
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface SetPlacaResidencia {
    suspend operator fun invoke(placa: String, flowApp: FlowApp, pos: Int): Boolean
}

class SetPlacaResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SetPlacaResidencia {

    override suspend fun invoke(placa: String, flowApp: FlowApp, pos: Int): Boolean {
        return try {
            when(flowApp) {
                FlowApp.ADD -> movEquipResidenciaRepository.setPlacaMovEquipResidencia(placa)
                FlowApp.CHANGE -> {
                    val movEquip = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
                    movEquipResidenciaRepository.updatePlacaMovEquipResidencia(placa, movEquip)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}