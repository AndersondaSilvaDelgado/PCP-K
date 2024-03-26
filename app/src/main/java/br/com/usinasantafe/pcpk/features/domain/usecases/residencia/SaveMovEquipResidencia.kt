package br.com.usinasantafe.pcpk.features.domain.usecases.residencia

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface SaveMovEquipResidencia {
    suspend operator fun invoke(): Boolean
    suspend operator fun invoke(movEquipResidencia: MovEquipResidencia): Boolean
}

class SaveMovEquipResidenciaImpl @Inject constructor(
    private val configRepository: ConfigRepository,
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SaveMovEquipResidencia {

    override suspend fun invoke(): Boolean {
        val config = configRepository.getConfig()
        return (movEquipResidenciaRepository.saveMovEquipResidencia(config.matricVigia!!, config.idLocal!!) != 0L)
    }

    override suspend fun invoke(movEquipResidencia: MovEquipResidencia): Boolean {
        val config = configRepository.getConfig()
        return (movEquipResidenciaRepository.saveMovEquipResidencia(config.matricVigia!!, config.idLocal!!, movEquipResidencia) != 0L)
    }

}