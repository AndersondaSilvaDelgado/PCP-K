package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import javax.inject.Inject

interface ReceiverSentDataMovEquipProprio {
    suspend operator fun invoke(movEquipProprioList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>): Boolean
}

class ReceiverSentDataMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
) : ReceiverSentDataMovEquipProprio {

    override suspend fun invoke(movEquipProprioList: List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipProprio>): Boolean {
        return movEquipProprioRepository.receiverSentMovEquipProprio(movEquipProprioList)
    }

}