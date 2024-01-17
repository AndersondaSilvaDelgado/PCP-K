package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SaveMovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetObservResidencia
import java.util.Date
import javax.inject.Inject

class SetObservResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val saveMovEquipResidencia: SaveMovEquipResidencia,
): SetObservResidencia {

    override suspend fun invoke(observ: String?, typeMov: TypeMov, pos: Int?): Boolean {
        try {
            if (typeMov == TypeMov.INPUT){
                if(!movEquipResidenciaRepository.setObservMovEquipResidencia(observ)) return false
                return saveMovEquipResidencia()
            } else {
                val movEquipResidencia = movEquipResidenciaRepository.listMovEquipResidenciaOpen()[pos!!]
                movEquipResidencia.observMovEquipResidencia = observ
                movEquipResidencia.tipoMovEquipResidencia = TypeMov.OUTPUT
                movEquipResidencia.dthrMovEquipResidencia = Date()
                return saveMovEquipResidencia(movEquipResidencia)
            }
        } catch (exception: Exception){
            return false
        }
    }

}