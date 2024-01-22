package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.proprio

import android.util.Log
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
        Log.i("PCP", "CHEGOU AKI 1")
        val idMov = movEquipProprioRepository.saveMovEquipProprio(config.matricVigia!!, config.idLocal!!)
        Log.i("PCP", "CHEGOU AKI 2")
        if(idMov == 0L) return false
        Log.i("PCP", "CHEGOU AKI 3")
        if(!movEquipProprioSegRepository.saveEquipSeg(idMov)) return false
        Log.i("PCP", "CHEGOU AKI 4")
        if(!movEquipProprioPassagRepository.savePassag(idMov)) return false
        Log.i("PCP", "CHEGOU AKI 5")
        return true
    }

}