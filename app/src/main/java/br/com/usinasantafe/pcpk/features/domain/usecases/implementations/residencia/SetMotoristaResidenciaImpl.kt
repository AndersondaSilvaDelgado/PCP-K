package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SetMotoristaResidencia
import javax.inject.Inject

class SetMotoristaResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SetMotoristaResidencia {

    override suspend fun invoke(motorista: String): Boolean {
        return try {
            movEquipResidenciaRepository.setMotoristaMovEquipResidencia(motorista)
        } catch (exception: Exception) {
            false
        }
    }

}