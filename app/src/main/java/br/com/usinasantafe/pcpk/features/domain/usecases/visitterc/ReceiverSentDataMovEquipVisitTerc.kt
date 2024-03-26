package br.com.usinasantafe.pcpk.features.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface ReceiverSentDataMovEquipVisitTerc {
    suspend operator fun invoke(movEquipVisitTercList: List<MovEquipVisitTerc>): Boolean
}

class ReceiverSentDataMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
) : ReceiverSentDataMovEquipVisitTerc {

    override suspend fun invoke(movEquipVisitTercList: List<MovEquipVisitTerc>): Boolean {
        return movEquipVisitTercRepository.receiverSentMovEquipVisitTerc(movEquipVisitTercList)
    }

}