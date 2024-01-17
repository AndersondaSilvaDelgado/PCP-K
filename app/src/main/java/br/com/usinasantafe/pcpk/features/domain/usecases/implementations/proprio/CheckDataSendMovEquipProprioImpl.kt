package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.CheckDataSendMovEquipProprio
import javax.inject.Inject

class CheckDataSendMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): CheckDataSendMovEquipProprio {

    override suspend fun invoke(): Boolean {
        return movEquipProprioRepository.checkMovSend()
    }

}