package br.com.usinasantafe.pcpk.domain.usecases.proprio

import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipProprioRepository
import javax.inject.Inject

interface CheckDataSendMovEquipProprio {
    suspend operator fun invoke(): Boolean
}

class CheckDataSendMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
): CheckDataSendMovEquipProprio {

    override suspend fun invoke(): Boolean {
        return movEquipProprioRepository.checkMovSend()
    }

}