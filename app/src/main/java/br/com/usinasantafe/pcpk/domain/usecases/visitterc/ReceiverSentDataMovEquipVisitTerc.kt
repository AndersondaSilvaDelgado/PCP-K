package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface ReceiverSentDataMovEquipVisitTerc {
    suspend operator fun invoke(movEquipVisitTercList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>): Boolean
}

class ReceiverSentDataMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
) : ReceiverSentDataMovEquipVisitTerc {

    override suspend fun invoke(movEquipVisitTercList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>): Boolean {
        return movEquipVisitTercRepository.receiverSentMovEquipVisitTerc(movEquipVisitTercList)
    }

}