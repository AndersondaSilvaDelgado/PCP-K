package br.com.usinasantafe.pcpk.features.domain.usecases.residencia

import android.util.Log
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import java.util.Date
import javax.inject.Inject

interface SetObservResidencia {
    suspend operator fun invoke(observ: String?, typeMov: TypeMov, pos: Int, flowApp: FlowApp): Boolean
}

class SetObservResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val saveMovEquipResidencia: SaveMovEquipResidencia,
) : SetObservResidencia {

    override suspend fun invoke(
        observ: String?,
        typeMov: TypeMov,
        pos: Int,
        flowApp: FlowApp
    ): Boolean {
        when (flowApp) {
            FlowApp.ADD -> {
                when (typeMov) {
                    TypeMov.INPUT -> {
                        if (!movEquipResidenciaRepository.setObservMovEquipResidencia(observ)) return false
                        return saveMovEquipResidencia()
                    }

                    TypeMov.OUTPUT -> {
                        val movEquipResidencia =
                            movEquipResidenciaRepository.listMovEquipResidenciaOpen()[pos]
                        if (!movEquipResidenciaRepository.setStatusCloseMov(movEquipResidencia)) return false
                        movEquipResidencia.observMovEquipResidencia = observ
                        movEquipResidencia.tipoMovEquipResidencia = TypeMov.OUTPUT
                        movEquipResidencia.dthrMovEquipResidencia = Date()
                        return saveMovEquipResidencia(movEquipResidencia)
                    }
                    else -> {
                        return false
                    }
                }
            }
            FlowApp.CHANGE -> {
                val movEquip = movEquipResidenciaRepository.listMovEquipResidenciaStarted()[pos]
                return movEquipResidenciaRepository.updateObservMovEquipResidencia(observ, movEquip)
            }
        }
    }

}