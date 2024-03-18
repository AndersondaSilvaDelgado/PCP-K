package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import android.util.Log
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
        when (flowApp) {
            FlowApp.ADD -> {
                when (typeMov!!) {
                    TypeMov.INPUT -> {
                        if (!movEquipResidenciaRepository.setObservMovEquipResidencia(observ)) return false
                        return saveMovEquipResidencia()
                    }

                    TypeMov.OUTPUT -> {
                        Log.i("PCPK", "CHEGOU AKI SET OBSERV 1")
                        val movEquipResidencia =
                            movEquipResidenciaRepository.listMovEquipResidenciaOpen()[pos]
                        Log.i("PCPK", "CHEGOU AKI SET OBSERV 2")
                        if (!movEquipResidenciaRepository.setStatusCloseMov(movEquipResidencia)) return false
                        Log.i("PCPK", "CHEGOU AKI SET OBSERV 3")
                        movEquipResidencia.observMovEquipResidencia = observ
                        movEquipResidencia.tipoMovEquipResidencia = TypeMov.OUTPUT
                        movEquipResidencia.dthrMovEquipResidencia = Date()
                        Log.i("PCPK", "CHEGOU AKI SET OBSERV 4")
                        return saveMovEquipResidencia(movEquipResidencia)
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