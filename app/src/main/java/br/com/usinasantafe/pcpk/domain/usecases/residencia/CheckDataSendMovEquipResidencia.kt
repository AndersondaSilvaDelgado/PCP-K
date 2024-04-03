package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface CheckDataSendMovEquipResidencia {
    suspend operator fun invoke(): Boolean
}

class CheckDataSendMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): CheckDataSendMovEquipResidencia {

    override suspend fun invoke(): Boolean {
        return movEquipResidenciaRepository.checkMovSend()
    }

}