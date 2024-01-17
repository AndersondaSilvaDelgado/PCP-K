package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetStatusSendCloseMovProprio
import javax.inject.Inject

class SetStatusSendCloseMovProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val startProcessSendData: StartProcessSendData,
): SetStatusSendCloseMovProprio {

    override suspend fun invoke(pos: Int): Boolean {
        try {
            val mov = movEquipProprioRepository.listMovEquipProprioOpen()[pos]
            if(!movEquipProprioRepository.setStatusSendClosedMov(mov)) return false
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}