package br.com.usinasantafe.pcpk.domain.usecases.residencia

import br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.domain.repositories.variable.MovEquipResidenciaRepository
import javax.inject.Inject

interface SendDataMovEquipResidencia {
    suspend operator fun invoke(): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>>
}

class SendDataMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val configRepository: ConfigRepository,
) : SendDataMovEquipResidencia {

    override suspend fun invoke(): Result<List<br.com.usinasantafe.pcpk.domain.entities.variable.MovEquipResidencia>> {
        val listMovEquipResidenciaSend = movEquipResidenciaRepository.listMovEquipResidenciaSend()
        val config = configRepository.getConfig()
        return movEquipResidenciaRepository.sendMovEquipResidencia(listMovEquipResidenciaSend, config.nroAparelhoConfig!!)
    }

}