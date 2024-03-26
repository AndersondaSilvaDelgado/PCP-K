package br.com.usinasantafe.pcpk.features.domain.usecases.residencia

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface ReceiverSentDataMovEquipResidencia {
    suspend operator fun invoke(movEquipResidenciaList: List<MovEquipResidencia>): Boolean
}

class ReceiverSentDataMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
) : ReceiverSentDataMovEquipResidencia {

    override suspend fun invoke(movEquipResidenciaList: List<MovEquipResidencia>): Boolean {
        return movEquipResidenciaRepository.receiverSentMovEquipResidencia(movEquipResidenciaList)
    }

}