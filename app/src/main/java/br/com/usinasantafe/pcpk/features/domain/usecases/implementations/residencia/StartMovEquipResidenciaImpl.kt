package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.StartMovEquipResidencia
import javax.inject.Inject

class StartMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): StartMovEquipResidencia {

    override suspend fun invoke(): Boolean {
        return try {
            movEquipResidenciaRepository.startMovEquipResidencia()
        } catch (e: Exception){
            false
        }
    }

}