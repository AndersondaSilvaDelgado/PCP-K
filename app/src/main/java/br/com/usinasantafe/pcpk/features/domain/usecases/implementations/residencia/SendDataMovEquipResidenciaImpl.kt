package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.residencia

import br.com.usinasantafe.pcpk.features.domain.entities.variable.MovEquipResidencia
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.pcpk.features.domain.repositories.variable.MovEquipResidenciaRepository
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia.SendDataMovEquipResidencia
import javax.inject.Inject

class SendDataMovEquipResidenciaImpl @Inject constructor(
    private val movEquipResidenciaRepository: MovEquipResidenciaRepository,
    private val configRepository: ConfigRepository,
) : SendDataMovEquipResidencia {

    override suspend fun invoke(): Result<List<MovEquipResidencia>> {
        val listMovEquipResidenciaSend = movEquipResidenciaRepository.listMovEquipResidenciaSend()
        val config = configRepository.getConfig()
        return movEquipResidenciaRepository.sendMovEquipResidencia(listMovEquipResidenciaSend, config.nroAparelhoConfig!!)
    }

}