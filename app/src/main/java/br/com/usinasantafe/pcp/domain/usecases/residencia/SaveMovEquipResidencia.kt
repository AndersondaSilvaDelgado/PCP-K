package br.com.usinasantafe.pcp.domain.usecases.residencia

import br.com.usinasantafe.pcp.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcp.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcp.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface SaveMovEquipResidencia {
    suspend operator fun invoke(): Boolean
    suspend operator fun invoke(movEquipResidencia: br.com.usinasantafe.pcp.domain.entities.variable.MovEquipResidencia): Boolean
}

class SaveMovEquipResidenciaImpl @Inject constructor(
    private val configRepository: ConfigRepository,
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
): SaveMovEquipResidencia {

    override suspend fun invoke(): Boolean {
        val config = configRepository.getConfig()
        return (movEquipResidenciaRepository.saveMovEquipResidencia(config.matricVigia!!, config.idLocal!!) != 0L)
    }

    override suspend fun invoke(movEquipResidencia: br.com.usinasantafe.pcp.domain.entities.variable.MovEquipResidencia): Boolean {
        val config = configRepository.getConfig()
        return (movEquipResidenciaRepository.saveMovEquipResidencia(config.matricVigia!!, config.idLocal!!, movEquipResidencia) != 0L)
    }

}