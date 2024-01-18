package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.visitterc

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipVisitTerc
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipVisitTercRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SendDataMovEquipVisitTerc
import javax.inject.Inject

class SendDataMovEquipVisitTercImpl @Inject constructor(
    private val movEquipVisitTercRepository: MovEquipVisitTercRepository,
    private val movEquipVisitTercPassagRepository: MovEquipVisitTercPassagRepository,
    private val configRepository: ConfigRepository,
) : SendDataMovEquipVisitTerc {

    override suspend fun invoke(): Result<List<MovEquipVisitTerc>> {
        val listMovEquipVisitTercSend = movEquipVisitTercRepository.listMovEquipVisitTercSend()
        val listMovEquipVisitTercFullSend = listMovEquipVisitTercSend.map { movEquipVisitTerc ->
            movEquipVisitTerc.movEquipVisitTercPassagList = movEquipVisitTercPassagRepository.listPassag(movEquipVisitTerc.idMovEquipVisitTerc!!)
            return@map movEquipVisitTerc
        }
        val config = configRepository.getConfig()
        return movEquipVisitTercRepository.sendMovEquipVisitTerc(listMovEquipVisitTercFullSend, config.nroAparelhoConfig!!)
    }

}