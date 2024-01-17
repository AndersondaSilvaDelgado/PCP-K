package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.ReceiverSentDataMovEquipVisitTerc
import javax.inject.Inject

class ReceiverSentDataMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
) : ReceiverSentDataMovEquipVisitTerc {

    override suspend fun invoke(movEquipVisitTercList: List<MovEquipVisitTerc>): Boolean {
        return movEquipVisitTercRepository.receiverSentMovEquipVisitTerc(movEquipVisitTercList)
    }

}