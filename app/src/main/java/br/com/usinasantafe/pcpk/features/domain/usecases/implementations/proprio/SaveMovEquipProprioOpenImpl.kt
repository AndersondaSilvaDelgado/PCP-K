package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioPassagRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipProprioSegRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SaveMovEquipProprioOpen
import javax.inject.Inject

class SaveMovEquipProprioOpenImpl @Inject constructor(
    private val configRepository: ConfigRepository,
    private val movEquipProprioRepository: MovEquipProprioRepository,
    private val movEquipProprioSegRepository: MovEquipProprioSegRepository,
    private val movEquipProprioPassagRepository: MovEquipProprioPassagRepository,
): SaveMovEquipProprioOpen {

    override suspend fun invoke(): Boolean {
        val config = configRepository.getConfig()
        val idMov = movEquipProprioRepository.saveMovEquipProprio(config.matricVigia!!, config.idLocal!!)
        if(idMov == 0) return false
        if(!movEquipProprioSegRepository.saveEquipSeg(idMov)) return false
        if(!movEquipProprioPassagRepository.savePassag(idMov)) return false
        return true
    }

}