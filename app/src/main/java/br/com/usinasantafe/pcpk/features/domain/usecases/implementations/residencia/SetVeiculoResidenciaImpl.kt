package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetVeiculoResidencia
import javax.inject.Inject

class SetVeiculoResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SetVeiculoResidencia {

    override suspend fun invoke(veiculo: String, flowApp: FlowApp, pos: Int): Boolean {
        return try {
            when (flowApp) {
                FlowApp.ADD -> movEquipResidenciaRepository.setVeiculoMovEquipResidencia(veiculo)
                FlowApp.CHANGE -> {
                    val movEquip = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
                    movEquipResidenciaRepository.updateVeiculoMovEquipResidencia(veiculo, movEquip)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}