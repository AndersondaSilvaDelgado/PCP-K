package br.com.usinasantafe.pcpk.features.domain.usecases.proprio

import br.com.usinasantafe.pcpk.common.utils.StatusSend
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.common.SetStatusSendConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.background.StartProcessSendData
import javax.inject.Inject

interface CloseSendAllMovProprio {
    suspend operator fun invoke(): Boolean
}

class CloseSendAllMovProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val setStatusSendConfig: SetStatusSendConfig,
    private val startProcessSendData: StartProcessSendData,
): CloseSendAllMovProprio {

    override suspend fun invoke(): Boolean {
        try{
            var check = true
            val movEquipProprioList = movEquipProprioRepository.listMovEquipProprioStarted()
            for (movEquipProprio in movEquipProprioList) {
                check = movEquipProprioRepository.setStatusSendMov(movEquipProprio)
            }
            if(!check) return false
            setStatusSendConfig(StatusSend.SEND)
            startProcessSendData()
        } catch (exception: Exception) {
            return false
        }
        return true
    }

}