package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.ReceiverSentDataMovEquipProprio

import javax.inject.Inject

class ReceiverSentDataMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
) : ReceiverSentDataMovEquipProprio {

    override suspend fun invoke(movEquipProprioList: List<MovEquipProprio>): Boolean {
        return movEquipProprioRepository.receiverSentMovEquipProprio(movEquipProprioList)
    }

}