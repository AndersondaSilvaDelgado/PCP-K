package br.com.usinasantafe.pcpk.features.domain.usecases.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface SetMotoristaResidencia {
    suspend operator fun invoke(motorista: String, flowApp: FlowApp, pos: Int): Boolean
}

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