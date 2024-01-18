package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetMotoristaResidencia
import javax.inject.Inject

class SetMotoristaResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SetMotoristaResidencia {

    override suspend fun invoke(motorista: String, flowApp: FlowApp, pos: Int): Boolean {
        return try {
            when(flowApp) {
                FlowApp.ADD -> movEquipResidenciaRepository.setMotoristaMovEquipResidencia(motorista)
                FlowApp.CHANGE -> {
                    val movEquip = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
                    movEquipResidenciaRepository.updateMotoristaMovEquipResidencia(motorista, movEquip)
                }
            }
        } catch (exception: Exception) {
            false
        }
    }

}