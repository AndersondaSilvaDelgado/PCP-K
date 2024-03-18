package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import android.util.Log
import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SaveMovEquipResidencia
import javax.inject.Inject

class SaveMovEquipResidenciaImpl @Inject constructor(
    private val configRepository: ConfigRepository,
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SaveMovEquipResidencia {

    override suspend fun invoke(): Boolean {
        val config = configRepository.getConfig()
        return (movEquipResidenciaRepository.saveMovEquipResidencia(config.matricVigia!!, config.idLocal!!) != 0L)
    }

    override suspend fun invoke(movEquipResidencia: MovEquipResidencia): Boolean {
        Log.i("PCPK", "CHEGOU AKI SAVE 1")
        val config = configRepository.getConfig()
        Log.i("PCPK", "CHEGOU AKI SAVE 2")
        return (movEquipResidenciaRepository.saveMovEquipResidencia(config.matricVigia!!, config.idLocal!!, movEquipResidencia) != 0L)
    }

}