package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SendDataMovEquipProprio
import javax.inject.Inject

class SendDataMovEquipProprioImpl @Inject constructor(
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
    private val configRepository: ConfigRepository,
) : SendDataMovEquipProprio {

    override suspend fun invoke(): Result<List<MovEquipProprio>> {
        val listMovEquipProprioSend = movEquipProprioRepository.listMovEquipProprioSend()
        val listMovEquipProprioFullSend = listMovEquipProprioSend.map { movEquipProprio ->
            movEquipProprio.movEquipProprioSegList = movEquipProprioSegRepository.listEquipSegIdMov(movEquipProprio.idMovEquipProprio!!)
            movEquipProprio.movEquipProprioPassagList = movEquipProprioPassagRepository.listPassagIdMov(movEquipProprio.idMovEquipProprio!!)
            return@map movEquipProprio
        }
        val config = configRepository.getConfig()
        return movEquipProprioRepository.sendMovEquipProprio(listMovEquipProprioFullSend, config.nroAparelhoConfig!!)
    }

}