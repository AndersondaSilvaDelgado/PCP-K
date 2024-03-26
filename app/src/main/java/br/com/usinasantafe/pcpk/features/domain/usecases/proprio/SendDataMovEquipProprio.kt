package br.com.usinasantafe.pcpk.features.domain.usecases.proprio

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import javax.inject.Inject

interface SendDataMovEquipProprio {
    suspend operator fun invoke(): Result<List<MovEquipProprio>>
}

class SendDataMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val configRepository: ConfigRepository,
) : SendDataMovEquipProprio {

    override suspend fun invoke(): Result<List<MovEquipProprio>> {
        val listMovEquipProprioSend = movEquipProprioRepository.listMovEquipProprioSend()
        val listMovEquipProprioFullSend = listMovEquipProprioSend.map { movEquipProprio ->
            movEquipProprio.movEquipProprioSegList = movEquipProprioSegRepository.listEquipSeg(movEquipProprio.idMovEquipProprio!!)
            movEquipProprio.movEquipProprioPassagList = movEquipProprioPassagRepository.listPassag(movEquipProprio.idMovEquipProprio!!)
            return@map movEquipProprio
        }
        val config = configRepository.getConfig()
        return movEquipProprioRepository.sendMovEquipProprio(listMovEquipProprioFullSend, config.nroAparelhoConfig!!)
    }

}