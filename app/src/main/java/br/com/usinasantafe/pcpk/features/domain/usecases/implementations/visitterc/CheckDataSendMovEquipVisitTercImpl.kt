package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.CheckDataSendMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.CheckDataSendMovEquipVisitTerc
import javax.inject.Inject

class CheckDataSendMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
): CheckDataSendMovEquipVisitTerc {

    override suspend fun invoke(): Boolean {
        return movEquipVisitTercRepository.checkMovSend()
    }

}