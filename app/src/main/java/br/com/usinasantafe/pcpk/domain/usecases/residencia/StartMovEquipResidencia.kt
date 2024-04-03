package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface StartMovEquipResidencia {
    suspend operator fun invoke(): Boolean
}

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