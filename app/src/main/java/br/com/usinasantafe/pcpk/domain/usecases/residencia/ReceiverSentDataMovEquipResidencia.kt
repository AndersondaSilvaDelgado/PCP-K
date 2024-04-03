package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface ReceiverSentDataMovEquipResidencia {
    suspend operator fun invoke(movEquipResidenciaList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>): Boolean
}

class ReceiverSentDataMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
) : ReceiverSentDataMovEquipResidencia {

    override suspend fun invoke(movEquipResidenciaList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>): Boolean {
        return movEquipResidenciaRepository.receiverSentMovEquipResidencia(movEquipResidenciaList)
    }

}