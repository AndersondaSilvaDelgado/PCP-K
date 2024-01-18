package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SaveMovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetObservResidencia
import java.util.Date
import javax.inject.Inject

class SetObservResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val saveMovEquipResidencia: SaveMovEquipResidencia,
) : SetObservResidencia {

    override suspend fun invoke(
        observ: String?,
        typeMov: TypeMov?,
        pos: Int,
        flowApp: FlowApp
    ): Boolean {
        return when (flowApp) {
            FlowApp.ADD -> {
                return when (typeMov!!) {
                    TypeMov.INPUT -> {
                        if (!movEquipResidenciaRepository.setObservMovEquipResidencia(observ)) false
                        saveMovEquipResidencia()
                    }

                    TypeMov.OUTPUT -> {
                        val movEquipResidencia =
                            movEquipResidenciaRepository.listMovEquipResidenciaOpen()[pos!!]
                        movEquipResidencia.observMovEquipResidencia = observ
                        movEquipResidencia.tipoMovEquipResidencia = TypeMov.OUTPUT
                        movEquipResidencia.dthrMovEquipResidencia = Date()
                        saveMovEquipResidencia(movEquipResidencia)
                    }
                }
            }

            FlowApp.CHANGE -> {
                val movEquip = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
                movEquipResidenciaRepository.updateObservMovEquipResidencia(observ, movEquip)
            }
        }
    }

}