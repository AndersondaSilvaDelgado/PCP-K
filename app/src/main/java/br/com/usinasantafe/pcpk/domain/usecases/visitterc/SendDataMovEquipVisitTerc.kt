package br.com.usinasantafe.pcpk.domain.usecases.visitterc

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc
import br.com.usinasantafe.pcpk.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipVisitTercRepository
import javax.inject.Inject

interface SendDataMovEquipVisitTerc {
    suspend operator fun invoke(): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>>
}

class SendDataMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
    private val configRepository: ConfigRepository,
) : SendDataMovEquipVisitTerc {

    override suspend fun invoke(): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipVisitTerc>> {
        val listMovEquipVisitTercSend = movEquipVisitTercRepository.listMovEquipVisitTercSend()
        val listMovEquipVisitTercFullSend = listMovEquipVisitTercSend.map { movEquipVisitTerc ->
            movEquipVisitTerc.movEquipVisitTercPassagList = movEquipVisitTercPassagRepository.listPassag(movEquipVisitTerc.idMovEquipVisitTerc!!)
            return@map movEquipVisitTerc
        }
        val config = configRepository.getConfig()
        return movEquipVisitTercRepository.sendMovEquipVisitTerc(listMovEquipVisitTercFullSend, config.nroAparelhoConfig!!)
    }

}