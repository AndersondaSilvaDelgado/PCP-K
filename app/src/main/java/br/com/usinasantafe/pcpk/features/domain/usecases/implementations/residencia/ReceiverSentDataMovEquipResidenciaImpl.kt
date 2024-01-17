package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.ReceiverSentDataMovEquipResidencia
import javax.inject.Inject

class ReceiverSentDataMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
) : ReceiverSentDataMovEquipResidencia {

    override suspend fun invoke(movEquipResidenciaList: List<MovEquipResidencia>): Boolean {
        return movEquipResidenciaRepository.receiverSentMovEquipResidencia(movEquipResidenciaList)
    }

}