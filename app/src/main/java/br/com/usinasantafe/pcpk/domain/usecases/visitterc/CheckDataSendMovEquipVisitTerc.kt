package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface CheckDataSendMovEquipVisitTerc {
    suspend operator fun invoke(): Boolean
}

class CheckDataSendMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): CheckDataSendMovEquipVisitTerc {

    override suspend fun invoke(): Boolean {
        return movEquipVisitTercRepository.checkMovSend()
    }

}